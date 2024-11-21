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
import java.time.Month;

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

        Month seasonMonth = harvest.getHarvestDate().getMonth();
        validateTreesForSeason(tree, seasonMonth);

        HarvestDetail harvestDetail = mapper.toEntity(requestDto);
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);

        HarvestDetail savedEntity = repository.save(harvestDetail);
        return mapper.toDto(savedEntity);
    }

    private void validateTreesForSeason(Tree tree, Month seasonMonth) {
        boolean exists = harvestDetailsRepository.existsByTreeAndHarvestSeason(tree, seasonMonth);

        if (exists) {
            throw new IllegalArgumentException("The tree has already been harvested for this season.");
        }
    }
}
