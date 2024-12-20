package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.TreeRequestDTO;
import org.youcode.citronix.dtos.response.TreeResponseDTO;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.mappers.TreeMapper;
import org.youcode.citronix.repositories.FieldRepository;
import org.youcode.citronix.repositories.TreeRepository;
import org.youcode.citronix.services.TreeService;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

@Service
@Transactional
@Validated
public class TreeServiceImpl extends GenericServiceImpl<Tree, Long, TreeRequestDTO, TreeResponseDTO> implements TreeService {
    private final FieldRepository fieldRepository;
    private final TreeRepository treeRepository;
    public TreeServiceImpl(TreeRepository repository, TreeMapper mapper, FieldRepository fieldRepository, TreeRepository treeRepository) {
        super(repository, mapper);
        this.fieldRepository = fieldRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public TreeResponseDTO create(TreeRequestDTO requestDto) {
        Field field = fieldRepository.findById(requestDto.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field with Id " + requestDto.fieldId() + " not found"));

        validateDensity(field);
        validatePlantingPeriod(requestDto.plantingDate());
        validatePlantingDateAgainstFarmCreationDate(requestDto.plantingDate(), field);

        Tree tree = mapper.toEntity(requestDto);
        tree.setField(field);

        int age = calculateAge(tree);
        validateTreeLifeSpan(age);

        Tree savedEntity = repository.save(tree);

        return mapper.toDto(savedEntity);
    }

    @Override
    public TreeResponseDTO update(Long id, TreeRequestDTO requestDto) {
        Tree existingTree = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tree with Id " + id + " not found"));

        Field field = fieldRepository.findById(requestDto.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field with Id " + requestDto.fieldId() + " not found"));

        validateDensity(field);
        validatePlantingPeriod(requestDto.plantingDate());
        validatePlantingDateAgainstFarmCreationDate(requestDto.plantingDate(), field);

        existingTree.setPlantingDate(requestDto.plantingDate());
        existingTree.setField(field);

        int age = calculateAge(existingTree);
        validateTreeLifeSpan(age);

        Tree updatedTree = repository.save(existingTree);
        return mapper.toDto(updatedTree);
    }

    private void validateDensity(Field field) {
        long treeCount = treeRepository.countByFieldId(field.getId());

        double densityLimit = 100.0;
        double maxTreesAllowed = densityLimit * field.getArea();

        if (treeCount >= maxTreesAllowed) {
            throw new IllegalArgumentException("Density limit exceeded: A field cannot have more than " + maxTreesAllowed + " trees.");
        }
    }

    private void validatePlantingPeriod(LocalDate plantingDate) {
        Month month = plantingDate.getMonth();
        if (month != Month.MARCH && month != Month.APRIL && month != Month.MAY) {
            throw new IllegalArgumentException("Trees can only be planted between March and May.");
        }
    }

    private int calculateAge(Tree tree) {
        if (tree.getPlantingDate() != null) {
            Period period = Period.between(tree.getPlantingDate(), LocalDate.now());
            return period.getYears();
        }
        return 0;
    }

    private void validateTreeLifeSpan(int age) {
        if (age > 20) {
            throw new IllegalArgumentException("Tree is too old to be productive (age > 20 years).");
        }
    }

    private void validatePlantingDateAgainstFarmCreationDate(LocalDate plantingDate, Field field) {
        LocalDate farmCreationDate = field.getFarm().getCreationDate();
        if (plantingDate.isBefore(farmCreationDate)) {
            throw new IllegalArgumentException("The planting date cannot be earlier than the farm's creation date.");
        }
    }

}
