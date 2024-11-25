package org.youcode.citronix.mappers;

import org.mapstruct.Mapper;
import org.youcode.citronix.common.Mapper.GenericMapper;
import org.youcode.citronix.dtos.request.SaleRequestDTO;
import org.youcode.citronix.dtos.response.SaleResponseDTO;
import org.youcode.citronix.entities.Sale;

@Mapper(componentModel = "spring")
public  interface SaleMapper extends GenericMapper<Sale, SaleRequestDTO, SaleResponseDTO> {
}
