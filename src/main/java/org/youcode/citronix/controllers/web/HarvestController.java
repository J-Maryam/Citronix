package org.youcode.citronix.controllers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.HarvestRequestDTO;
import org.youcode.citronix.dtos.response.HarvestResponseDTO;
import org.youcode.citronix.entities.Harvest;
import org.youcode.citronix.services.HarvestService;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController extends GenericControllerImpl<Harvest, Long, HarvestRequestDTO, HarvestResponseDTO> {
    public HarvestController(HarvestService service) {
        super(service);
    }
}
