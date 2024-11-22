package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.HarvestDetailRequestDTO;
import org.youcode.citronix.dtos.response.HarvestDetailResponseDTO;
import org.youcode.citronix.entities.Harvest;
import org.youcode.citronix.entities.HarvestDetail;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.entities.embeddable.HarvestDetailId;
import org.youcode.citronix.mappers.HarvestDetailsMapper;
import org.youcode.citronix.repositories.HarvestDetailsRepository;
import org.youcode.citronix.repositories.HarvestRepository;
import org.youcode.citronix.repositories.TreeRepository;
import org.youcode.citronix.services.HarvestDetailsService;

import java.time.LocalDate;
import java.time.Period;

@Service
@Transactional
@Validated
public class HarvestDetailsServiceImpl extends GenericServiceImpl<HarvestDetail, HarvestDetailId, HarvestDetailRequestDTO, HarvestDetailResponseDTO> implements HarvestDetailsService {

    private final HarvestDetailsRepository harvestDetailsRepository;
    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    public HarvestDetailsServiceImpl(HarvestDetailsRepository repository, HarvestDetailsMapper mapper, HarvestDetailsRepository harvestDetailsRepository, HarvestRepository harvestRepository, TreeRepository treeRepository) {
        super(repository, mapper);
        this.harvestDetailsRepository = repository;
        this.harvestRepository = harvestRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public HarvestDetailResponseDTO create(HarvestDetailRequestDTO requestDto) {

        Harvest harvest = harvestRepository.findById(requestDto.harvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest with Id " + requestDto.harvestId() + " not found"));

        Tree tree = treeRepository.findById(requestDto.treeId())
                .orElseThrow(() -> new EntityNotFoundException("Tree with Id " + requestDto.treeId() + " not found"));

        if (!tree.getField().getId().equals(harvest.getField().getId())) {
            throw new IllegalArgumentException(" Tree does not belong to the same field as the harvest");
        }

        boolean alreadyHarvested = harvestDetailsRepository.existsByTreeAndHarvest_Season(
                tree,
                harvest.getSeason()
        );

        if (alreadyHarvested) {
            throw new IllegalArgumentException(" The tree has already been harvested for this season.");
        }

        validateHarvestedQuantity(tree, requestDto.quantity());
        HarvestDetailId harvestDetailId = new HarvestDetailId(requestDto.treeId(), requestDto.harvestId());

        HarvestDetail harvestDetail = mapper.toEntity(requestDto);
        harvestDetail.setId(harvestDetailId);
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);
        harvestDetail.setQuantity(requestDto.quantity());

        updateHarvestTotalQuantity(harvest, requestDto.quantity());

        HarvestDetail savedEntity = repository.save(harvestDetail);
        return mapper.toDto(savedEntity);
    }

    @Override
    public void delete(HarvestDetailId id) {
        HarvestDetail harvestDetail = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with Id " + id + " not found"));

        Harvest harvest = harvestDetail.getHarvest();
        double newTotalQuantity = harvest.getTotalQuantity() - harvestDetail.getQuantity();
        harvest.setTotalQuantity(Math.max(0, newTotalQuantity));
        harvestRepository.save(harvest);

        repository.delete(harvestDetail);
    }

    private void validateHarvestedQuantity(Tree tree, double harvestedQuantity) {
        int treeAge = calculateTreeAge(tree);
        double expectedProductivity = calculateExpectedProductivity(treeAge);

        double maxAllowedQuantity = expectedProductivity * 1.2;

        if (harvestedQuantity > maxAllowedQuantity) {
            throw new IllegalArgumentException(
                    String.format("Harvested quantity (%.2f kg) exceeds maximum allowed quantity (%.2f kg) for tree age %d years",
                            harvestedQuantity, maxAllowedQuantity, treeAge)
            );
        }
    }

    private int calculateTreeAge(Tree tree) {
        return Period.between(tree.getPlantingDate(), LocalDate.now()).getYears();
    }

    private double calculateExpectedProductivity(int age) {
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else if (age <= 20) {
            return 20.0;
        } else {
            return 0.0;
        }
    }

    private void updateHarvestTotalQuantity(Harvest harvest, double additionalQuantity) {
        double newTotalQuantity = harvest.getTotalQuantity() + additionalQuantity;
        harvest.setTotalQuantity(newTotalQuantity);
        harvestRepository.save(harvest);
    }

}
