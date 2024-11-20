package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.request.FieldRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.dtos.response.FieldResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.mappers.FarmMapper;
import org.youcode.citronix.mappers.FieldMapper;
import org.youcode.citronix.repositories.FarmRepository;
import org.youcode.citronix.repositories.FieldRepository;
import org.youcode.citronix.services.FarmService;
import org.youcode.citronix.services.FieldService;

@Service
@Transactional
@Validated
public class FieldServiceImpl extends GenericServiceImpl<Field, Long, FieldRequestDTO, FieldResponseDTO> implements FieldService {
    public FieldServiceImpl(FieldRepository repository, FieldMapper mapper) {
        super(repository, mapper);
    }


}
