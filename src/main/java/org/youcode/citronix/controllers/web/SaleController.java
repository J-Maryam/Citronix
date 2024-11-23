package org.youcode.citronix.controllers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.SaleRequestDTO;
import org.youcode.citronix.dtos.response.SaleResponseDTO;
import org.youcode.citronix.entities.Sale;
import org.youcode.citronix.services.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController extends GenericControllerImpl<Sale, Long, SaleRequestDTO, SaleResponseDTO> {

    public SaleController(SaleService service) {
        super(service);
    }
}
