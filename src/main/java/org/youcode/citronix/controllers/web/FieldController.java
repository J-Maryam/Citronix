package org.youcode.citronix.controllers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.FieldRequestDTO;
import org.youcode.citronix.dtos.response.FieldResponseDTO;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.services.FieldService;

@RestController
@RequestMapping("/api/fields")
public class FieldController extends GenericControllerImpl<Field,Long, FieldRequestDTO, FieldResponseDTO> {
    public FieldController(FieldService service) {
        super(service);
    }
}
