package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.SaleRequestDTO;
import org.youcode.citronix.dtos.response.SaleResponseDTO;
import org.youcode.citronix.entities.Sale;
import org.youcode.citronix.mappers.SaleMapper;
import org.youcode.citronix.repositories.SaleRepository;
import org.youcode.citronix.services.SaleService;

@Service
@Validated
@Transactional
public class SaleServiceImpl extends GenericServiceImpl<Sale, Long, SaleRequestDTO, SaleResponseDTO> implements SaleService {

    public SaleServiceImpl(SaleRepository repository, SaleMapper mapper) {
        super(repository, mapper);
    }
}
