package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.SaleRequestDTO;
import org.youcode.citronix.dtos.response.SaleResponseDTO;
import org.youcode.citronix.entities.Harvest;
import org.youcode.citronix.entities.Sale;
import org.youcode.citronix.mappers.SaleMapper;
import org.youcode.citronix.repositories.HarvestRepository;
import org.youcode.citronix.repositories.SaleRepository;
import org.youcode.citronix.services.SaleService;

@Service
@Validated
@Transactional
public class SaleServiceImpl extends GenericServiceImpl<Sale, Long, SaleRequestDTO, SaleResponseDTO> implements SaleService {

    private final HarvestRepository harvestRepository;
    public SaleServiceImpl(SaleRepository repository, SaleMapper mapper, HarvestRepository harvestRepository) {
        super(repository, mapper);
        this.harvestRepository = harvestRepository;
    }

    @Override
    public SaleResponseDTO create(SaleRequestDTO requestDto) {
        Harvest existingHarvest = harvestRepository.findById(requestDto.harvestId())
                .orElseThrow( () -> new EntityNotFoundException("Harvest with Id " + requestDto.harvestId() + " not found"));

        if (requestDto.quantity() > existingHarvest.getTotalQuantity()) {
            throw new IllegalArgumentException("The quantity demanded exceeds the quantity available in the harvest.");
        }

        existingHarvest.setTotalQuantity(existingHarvest.getTotalQuantity() - requestDto.quantity());
        harvestRepository.save(existingHarvest);

        Sale sale = mapper.toEntity(requestDto);
        sale.setHarvest(existingHarvest);
        sale.getRevenue();
        Sale saved = repository.save(sale);
        return mapper.toDto(saved);
    }
}
