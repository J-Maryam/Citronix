package org.youcode.citronix.controllers.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.citronix.common.controllers.GenericControllerImpl;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.dtos.response.FarmSearchCriteria;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.services.FarmService;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController extends GenericControllerImpl<Farm,Long, FarmRequestDTO, FarmResponseDTO> {

    private final FarmService farmService;
    public FarmController(FarmService service, FarmService farmService) {
        super(service);
        this.farmService = farmService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<FarmResponseDTO>> searchFarms(FarmSearchCriteria criteria) {
        List<FarmResponseDTO> results = farmService.searchFarms(criteria);
        return ResponseEntity.ok(results);
    }
}
