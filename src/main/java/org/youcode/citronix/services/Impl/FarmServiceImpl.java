package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.mappers.FarmMapper;
import org.youcode.citronix.repositories.FarmRepository;
import org.youcode.citronix.services.FarmService;

@Service
@Transactional
@Validated
public class FarmServiceImpl extends GenericServiceImpl<Farm, Long, FarmRequestDTO, FarmResponseDTO> implements FarmService {
        public FarmServiceImpl(FarmRepository repository, FarmMapper mapper) {
            super(repository, mapper);
        }

    @Override
    public FarmResponseDTO update(Long id, FarmRequestDTO requestDto) {
        Farm existingFerme = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with Id " + id + " not found"));


        if (requestDto.name() != null) {
            existingFerme.setName(requestDto.name());
        }
        if (requestDto.location() != null) {
            existingFerme.setLocation(requestDto.location());
        }
        if (requestDto.area() != 0) {
            existingFerme.setArea(requestDto.area());
        }
        if (requestDto.creationDate() != null) {
            existingFerme.setCreationDate(requestDto.creationDate());
        }

        Farm updatedEntity = repository.save(existingFerme);
        return mapper.toDto(updatedEntity);
    }
}
