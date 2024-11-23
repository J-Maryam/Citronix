package org.youcode.citronix.services;

import org.youcode.citronix.common.services.GenericService;
import org.youcode.citronix.dtos.request.SaleRequestDTO;
import org.youcode.citronix.dtos.response.SaleResponseDTO;
import org.youcode.citronix.entities.Sale;

public interface SaleService extends GenericService<Sale, Long, SaleRequestDTO, SaleResponseDTO> {
}
