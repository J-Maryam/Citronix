package org.youcode.citronix.controllers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.FermeRequestDTO;
import org.youcode.citronix.dtos.response.FermeResponseDTO;
import org.youcode.citronix.entities.Ferme;
import org.youcode.citronix.services.FermeService;

@RestController
@RequestMapping("/api/farms")
public class FermeController extends GenericControllerImpl<Ferme,Long, FermeRequestDTO, FermeResponseDTO> {
    public FermeController(FermeService service) {
        super(service);
    }
}
