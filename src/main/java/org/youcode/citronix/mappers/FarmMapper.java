package org.youcode.citronix.mappers;

import org.mapstruct.Mapper;
import org.youcode.citronix.common.Mapper.GenericMapper;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.entities.Farm;

@Mapper(componentModel = "spring")
public interface FarmMapper extends GenericMapper<Farm, FarmRequestDTO, FarmResponseDTO> {
}
