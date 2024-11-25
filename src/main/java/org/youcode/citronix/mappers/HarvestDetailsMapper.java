package org.youcode.citronix.mappers;

import org.mapstruct.Mapper;
import org.youcode.citronix.common.Mapper.GenericMapper;
import org.youcode.citronix.dtos.request.HarvestDetailRequestDTO;
import org.youcode.citronix.dtos.response.HarvestDetailResponseDTO;
import org.youcode.citronix.entities.HarvestDetail;

@Mapper(componentModel = "spring")
public interface HarvestDetailsMapper extends GenericMapper<HarvestDetail, HarvestDetailRequestDTO, HarvestDetailResponseDTO> {
}
