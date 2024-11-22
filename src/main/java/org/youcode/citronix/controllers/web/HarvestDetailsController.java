package org.youcode.citronix.controllers.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.citronix.common.ApiResponse;
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

    @GetMapping("/{harvestId}/{treeId}")
    public ResponseEntity<HarvestDetailResponseDTO> getById(@PathVariable("harvestId") Long harvestId, @PathVariable("treeId") Long treeId) {
        HarvestDetailId id = new HarvestDetailId(treeId, harvestId);
        HarvestDetailResponseDTO response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{harvestId}/{treeId}")
    public ResponseEntity<HarvestDetailResponseDTO> update(@PathVariable Long harvestId,
                                                           @PathVariable Long treeId,
                                                           @RequestBody HarvestDetailRequestDTO requestDto) {
        HarvestDetailId id = new HarvestDetailId(treeId, harvestId);
        HarvestDetailResponseDTO updatedResponse = service.update(id, requestDto);
        return ResponseEntity.ok(updatedResponse);
    }

    @DeleteMapping("/{harvestId}/{treeId}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long harvestId, @PathVariable Long treeId) {
        HarvestDetailId id = new HarvestDetailId(treeId, harvestId);
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Harvest details deleted successfully"));
    }
}
