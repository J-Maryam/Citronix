package org.youcode.citronix.mappers;

import org.mapstruct.Mapper;
import org.youcode.citronix.common.GenericMapper;
import org.youcode.citronix.dtos.request.TreeRequestDTO;
import org.youcode.citronix.dtos.response.TreeResponseDTO;
import org.youcode.citronix.entities.Tree;

@Mapper(componentModel = "spring")
public interface TreeMapper extends GenericMapper<Tree, TreeRequestDTO, TreeResponseDTO> {
}
