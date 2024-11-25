package org.youcode.citronix.mappers;

import org.mapstruct.Mapper;
import org.youcode.citronix.common.Mapper.GenericMapper;
import org.youcode.citronix.dtos.request.HarvestRequestDTO;
import org.youcode.citronix.dtos.response.HarvestResponseDTO;
import org.youcode.citronix.entities.Harvest;

@Mapper(componentModel = "spring")
public interface HarvestMapper extends GenericMapper<Harvest, HarvestRequestDTO, HarvestResponseDTO> {
}
