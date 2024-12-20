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
        if (newFieldArea < 0.1) {
            throw new IllegalArgumentException("Field area must be at least 0.1 hectare.");
        }

        if (newFieldArea > farm.getArea() / 2) {
            throw new IllegalArgumentException("Field area cannot exceed 50% of the total farm area.");
        }

        long fieldCount = farm.getFields().stream().count();
        if (fieldCount >= 10 && (existingField == null || !farm.getFields().contains(existingField))) {
            throw new IllegalArgumentException("A farm cannot have more than 10 fields.");
        }

        double totalFieldArea = calculateTotalFieldArea(farm, existingField);
        double adjustedTotalArea = totalFieldArea + newFieldArea;

        if (adjustedTotalArea >= farm.getArea()) {
            throw new IllegalArgumentException("The total area of fields exceeds the farm's area. Maximum allowed: "
                    + farm.getArea() + " hectares, current total: " + adjustedTotalArea + " hectares.");
        }
    }

    private double calculateTotalFieldArea(Farm farm, Field existingField) {
        return farm.getFields().stream()
                .filter(field -> existingField == null || !field.getId().equals(existingField.getId()))
                .mapToDouble(Field::getArea)
                .sum();
    }

}
