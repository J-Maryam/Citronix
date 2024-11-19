package org.youcode.citronix.mappers;

import org.mapstruct.Mapper;
import org.youcode.citronix.common.GenericMapper;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FermeResponseDTO;
import org.youcode.citronix.entities.Farm;

@Mapper(componentModel = "spring")
public interface FermeMapper extends GenericMapper<Farm, FarmRequestDTO, FermeResponseDTO> {
}
