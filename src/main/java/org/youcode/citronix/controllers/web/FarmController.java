package org.youcode.citronix.controllers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.services.FarmService;

@RestController
@RequestMapping("/api/farms")
public class FarmController extends GenericControllerImpl<Farm,Long, FarmRequestDTO, FarmResponseDTO> {
    public FarmController(FarmService service) {
        super(service);
    }
}
