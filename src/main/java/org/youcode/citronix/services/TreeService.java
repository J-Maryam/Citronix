package org.youcode.citronix.services;

import org.youcode.citronix.common.services.GenericService;
import org.youcode.citronix.dtos.request.TreeRequestDTO;
import org.youcode.citronix.dtos.response.TreeResponseDTO;
import org.youcode.citronix.entities.Tree;

public interface TreeService extends GenericService<Tree, Long, TreeRequestDTO, TreeResponseDTO> {
}
