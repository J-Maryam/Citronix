package org.youcode.citronix.services;

import org.youcode.citronix.common.services.GenericService;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.entities.Farm;

public interface FarmService extends GenericService<Farm, Long, FarmRequestDTO, FarmResponseDTO> {
}