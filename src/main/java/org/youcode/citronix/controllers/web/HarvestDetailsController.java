package org.youcode.citronix.controllers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.HarvestDetailRequestDTO;
import org.youcode.citronix.dtos.response.HarvestDetailResponseDTO;
import org.youcode.citronix.entities.HarvestDetail;
import org.youcode.citronix.entities.embeddable.HarvestDetailId;
import org.youcode.citronix.services.HarvestDetailsService;

@RestController
@RequestMapping("/api/harvest_details")
public class HarvestDetailsController extends GenericControllerImpl<HarvestDetail, HarvestDetailId, HarvestDetailRequestDTO, HarvestDetailResponseDTO> {

    public HarvestDetailsController(HarvestDetailsService service) {
        super(service);
    }
}
