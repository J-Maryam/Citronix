package org.youcode.citronix.mappers;

import org.mapstruct.Mapper;
import org.youcode.citronix.common.GenericMapper;
import org.youcode.citronix.dtos.request.FieldRequestDTO;
import org.youcode.citronix.dtos.response.FieldResponseDTO;
import org.youcode.citronix.entities.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper extends GenericMapper<Field, FieldRequestDTO, FieldResponseDTO> {
}
