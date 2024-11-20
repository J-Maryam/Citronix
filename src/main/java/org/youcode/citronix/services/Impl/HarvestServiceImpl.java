package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.HarvestRequestDTO;
import org.youcode.citronix.dtos.response.HarvestResponseDTO;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.entities.Harvest;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.mappers.HarvestMapper;
import org.youcode.citronix.repositories.FieldRepository;
import org.youcode.citronix.repositories.HarvestRepository;
import org.youcode.citronix.repositories.TreeRepository;
import org.youcode.citronix.services.HarvestService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
@Transactional
@Validated
public class HarvestServiceImpl extends GenericServiceImpl<Harvest, Long, HarvestRequestDTO, HarvestResponseDTO> implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;

    public HarvestServiceImpl(HarvestRepository repository, HarvestMapper mapper, HarvestRepository harvestRepository, TreeRepository treeRepository, FieldRepository fieldRepository) {
        super(repository, mapper);
        this.harvestRepository = harvestRepository;
        this.treeRepository = treeRepository;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public HarvestResponseDTO create(HarvestRequestDTO requestDto) {
        Field field = fieldRepository.findById(requestDto.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field with Id " + requestDto.fieldId() + " not found"));

        validateSingleHarvestPerSeason(field, requestDto.harvestDate());
        validateTreesForSeason(field, requestDto.harvestDate());

        Harvest harvest = mapper.toEntity(requestDto);
        harvest.setField(field);

        Harvest savedHarvest = repository.save(harvest);

        return mapper.toDto(savedHarvest);
    }

    private void validateSingleHarvestPerSeason(Field field, LocalDate harvestDate) {
        String currentSeason = getSeason(harvestDate);
        boolean exists = harvestRepository.existsByFieldAndSeason(field.getId(), currentSeason);

        if (exists) {
            throw new IllegalArgumentException("Field already has a harvest for the season: " + currentSeason);
        }
    }

    private void validateTreesForSeason(Field field, LocalDate harvestDate) {
        String currentSeason = getSeason(harvestDate);
        List<Tree> trees = treeRepository.findByFieldId(field.getId());

        trees.forEach(tree -> {
            boolean harvested = harvestRepository.existsByTreeAndSeason(tree.getId(), currentSeason);
            if (harvested) {
                throw new IllegalArgumentException("Tree with ID " + tree.getId() + " has already been harvested this season: " + currentSeason);
            }
        });
    }

    private String getSeason(LocalDate date) {
        Month month = date.getMonth();
        if (month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY) {
            return "Winter";
        } else if (month == Month.MARCH || month == Month.APRIL || month == Month.MAY) {
            return "Spring";
        } else if (month == Month.JUNE || month == Month.JULY || month == Month.AUGUST) {
            return "Summer";
        } else {
            return "Autumn";
        }
    }
}
