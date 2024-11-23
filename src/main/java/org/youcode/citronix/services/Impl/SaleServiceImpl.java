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

    @Override
    public SaleResponseDTO update(Long id, SaleRequestDTO requestDto) {
        Sale existingSale = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale with Id " + id + " not found"));

        Harvest existingHarvest = harvestRepository.findById(requestDto.harvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest with Id " + requestDto.harvestId() + " not found"));

        double availableQuantity = existingHarvest.getTotalQuantity() + existingSale.getQuantity();

        if (requestDto.quantity() > availableQuantity) {
            throw new IllegalArgumentException("The quantity demanded exceeds the available quantity in the harvest.");
        }

        existingHarvest.setTotalQuantity(availableQuantity - requestDto.quantity());
        harvestRepository.save(existingHarvest);

        existingSale.setSaleDate(requestDto.saleDate());
        existingSale.setUnitPrice(requestDto.unitPrice());
        existingSale.setQuantity(requestDto.quantity());
        existingSale.setClient(requestDto.client());
        existingSale.setHarvest(existingHarvest);

        Sale updatedSale = repository.save(existingSale);
        return mapper.toDto(updatedSale);
    }

    @Override
    public void delete(Long id) {
        Sale existingSale = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale with Id " + id + " not found"));

        Harvest associatedHarvest = existingSale.getHarvest();

        associatedHarvest.setTotalQuantity(associatedHarvest.getTotalQuantity() + existingSale.getQuantity());
        harvestRepository.save(associatedHarvest);

        repository.delete(existingSale);
    }

}
