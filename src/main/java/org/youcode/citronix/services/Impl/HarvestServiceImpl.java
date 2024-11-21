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
import org.youcode.citronix.mappers.HarvestMapper;
import org.youcode.citronix.repositories.FieldRepository;
import org.youcode.citronix.repositories.HarvestRepository;
import org.youcode.citronix.services.HarvestService;

@Service
@Transactional
@Validated
public class HarvestServiceImpl extends GenericServiceImpl<Harvest, Long, HarvestRequestDTO, HarvestResponseDTO> implements HarvestService {

    private final FieldRepository fieldRepository;

    public HarvestServiceImpl(HarvestRepository repository, HarvestMapper mapper, FieldRepository fieldRepository) {
        super(repository, mapper);
        this.fieldRepository = fieldRepository;
    }

    @Override
    public HarvestResponseDTO create(HarvestRequestDTO requestDto) {
        Field field = fieldRepository.findById(requestDto.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field with Id " + requestDto.fieldId() + " not found"));

        Harvest harvest = mapper.toEntity(requestDto);
        harvest.setField(field);

        Harvest savedHarvest = repository.save(harvest);

        return mapper.toDto(savedHarvest);
    }

    @Override
    public HarvestResponseDTO update(Long id, HarvestRequestDTO requestDto) {
        Harvest existingHarvest = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest with Id " + id + " not found"));

        Field field = fieldRepository.findById(requestDto.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field with Id " + requestDto.fieldId() + " not found"));

        existingHarvest.setHarvestDate(requestDto.harvestDate());
        existingHarvest.setTotalQuantity(requestDto.totalQuantity());
        existingHarvest.setField(field);

        Harvest updatedHarvest = repository.save(existingHarvest);

        return mapper.toDto(updatedHarvest);
    }

}
