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

        Tree tree = mapper.toEntity(requestDto);
        tree.setField(field);

        int age = calculateAge(tree);
        validateTreeLifeSpan(age);

        Tree savedEntity = repository.save(tree);
        return mapper.toDto(savedEntity);
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
        if (tree.getPlantationDate() != null) {
            Period period = Period.between(tree.getPlantationDate(), LocalDate.now());
            return period.getYears();
        }
        return 0;
    }

    private double calculateProductivity(int age) {
        if (age < 3) {
            return 2.5;
        } else if (age > 3 && age <= 10) {
            return 12;
        } else if (age > 10) {
            return 20;
        } else {
            return 0.0;
        }
    }

    private void validateTreeLifeSpan(int age) {
        if (age > 20) {
            throw new IllegalArgumentException("Tree is too old to be productive (age > 20 years).");
        }
    }
}
