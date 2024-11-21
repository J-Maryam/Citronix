package org.youcode.citronix.services;

import org.youcode.citronix.common.services.GenericService;
import org.youcode.citronix.dtos.request.HarvestDetailRequestDTO;
import org.youcode.citronix.dtos.response.HarvestDetailResponseDTO;
import org.youcode.citronix.entities.HarvestDetail;
import org.youcode.citronix.entities.embeddable.HarvestDetailId;

public interface HarvestDetailsService extends GenericService<HarvestDetail, HarvestDetailId, HarvestDetailRequestDTO, HarvestDetailResponseDTO> {
}
