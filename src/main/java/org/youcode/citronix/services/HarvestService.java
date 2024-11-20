package org.youcode.citronix.services;

import org.youcode.citronix.common.services.GenericService;
import org.youcode.citronix.dtos.request.HarvestRequestDTO;
import org.youcode.citronix.dtos.response.HarvestResponseDTO;
import org.youcode.citronix.entities.Harvest;

public interface HarvestService extends GenericService<Harvest, Long, HarvestRequestDTO, HarvestResponseDTO> {
}
