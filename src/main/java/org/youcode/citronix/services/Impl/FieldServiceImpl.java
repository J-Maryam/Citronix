package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.FieldRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.dtos.response.FieldResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.mappers.FieldMapper;
import org.youcode.citronix.repositories.FarmRepository;
import org.youcode.citronix.repositories.FieldRepository;
import org.youcode.citronix.services.FarmService;
import org.youcode.citronix.services.FieldService;

@Service
@Transactional
@Validated
public class FieldServiceImpl extends GenericServiceImpl<Field, Long, FieldRequestDTO, FieldResponseDTO> implements FieldService {
    private final FarmRepository farmRepository;
    public FieldServiceImpl(FieldRepository repository, FieldMapper mapper, FarmRepository farmRepository) {
        super(repository, mapper);
        this.farmRepository = farmRepository;
    }

    @Override
    public FieldResponseDTO create(FieldRequestDTO requestDto) {
        Farm existingFarm = farmRepository.findById(requestDto.farmId())
                .orElseThrow(() -> new EntityNotFoundException("Farm with Id " + requestDto.farmId() + " not found"));

        Field entity = mapper.toEntity(requestDto);
        entity.setFarm(existingFarm);
        Field savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity);
    }
}
