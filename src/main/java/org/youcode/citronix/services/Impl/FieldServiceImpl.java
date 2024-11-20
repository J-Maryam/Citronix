package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.FieldRequestDTO;
import org.youcode.citronix.dtos.response.FieldResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.mappers.FieldMapper;
import org.youcode.citronix.repositories.FarmRepository;
import org.youcode.citronix.repositories.FieldRepository;
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

        validateFieldArea(existingFarm, requestDto.area(), null);
        Field entity = mapper.toEntity(requestDto);
        entity.setFarm(existingFarm);
        Field savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity);
    }

    @Override
    public FieldResponseDTO update(Long id, FieldRequestDTO requestDto) {
        Field existingField = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Field with Id " + id + " not found"));

        Farm farm = farmRepository.findById(requestDto.farmId())
                .orElseThrow(() -> new EntityNotFoundException("Farm with Id " + requestDto.farmId() + " not found"));

        validateFieldArea(farm, requestDto.area(), existingField);
        existingField.setArea(requestDto.area());
        if (!existingField.getFarm().getId().equals(farm.getId())) {
            existingField.setFarm(farm);
        }

        Field updatedField = repository.save(existingField);
        return mapper.toDto(updatedField);
    }

    private void validateFieldArea(Farm farm, double newFieldArea, Field existingField) {
        double totalFieldArea = farm.getFields().stream()
                .filter(field -> existingField == null || !field.getId().equals(existingField.getId())) // Exclure le champ actuel s'il existe
                .mapToDouble(Field::getArea)
                .sum();

        double adjustedTotalArea = totalFieldArea + newFieldArea;

        if (adjustedTotalArea >= farm.getArea()) {
            throw new IllegalArgumentException("The total area of fields exceeds the farm's area. Maximum allowed: "
                    + farm.getArea() + " hectares, current total: " + adjustedTotalArea + " hectares.");
        }
    }

}
