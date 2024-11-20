package org.youcode.citronix.controllers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.TreeRequestDTO;
import org.youcode.citronix.dtos.response.TreeResponseDTO;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.services.TreeService;

@RestController
@RequestMapping("/api/trees")
public class TreeController extends GenericControllerImpl<Tree, Long, TreeRequestDTO, TreeResponseDTO> {

    public TreeController(TreeService service) {
        super(service);
    }
}
