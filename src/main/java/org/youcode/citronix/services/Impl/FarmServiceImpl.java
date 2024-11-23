package org.youcode.citronix.services.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.dtos.response.FarmSearchCriteria;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.mappers.FarmMapper;
import org.youcode.citronix.repositories.FarmRepository;
import org.youcode.citronix.services.FarmService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Validated
public class FarmServiceImpl extends GenericServiceImpl<Farm, Long, FarmRequestDTO, FarmResponseDTO> implements FarmService {

    @PersistenceContext
    private EntityManager entityManager;

    public FarmServiceImpl(FarmRepository repository, FarmMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public FarmResponseDTO update(Long id, FarmRequestDTO requestDto) {
        Farm existingFerme = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with Id " + id + " not found"));

        if (requestDto.name() != null) {
            existingFerme.setName(requestDto.name());
        }
        if (requestDto.location() != null) {
            existingFerme.setLocation(requestDto.location());
        }
        if (requestDto.area() != 0) {
            existingFerme.setArea(requestDto.area());
        }
        if (requestDto.creationDate() != null) {
            existingFerme.setCreationDate(requestDto.creationDate());
        }

        Farm updatedEntity = repository.save(existingFerme);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public List<FarmResponseDTO> searchFarms(FarmSearchCriteria criteria) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> farmRoot = query.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.name() != null && !criteria.name().isEmpty()) {
            predicates.add(cb.like(cb.lower(farmRoot.get("name")), "%" + criteria.name().toLowerCase() + "%"));
        }
        if (criteria.location() != null && !criteria.location().isEmpty()) {
            predicates.add(cb.like(cb.lower(farmRoot.get("location")), "%" + criteria.location().toLowerCase() + "%"));
        }

        if (criteria.minArea() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farmRoot.get("area"), criteria.minArea()));
        }

        if (criteria.maxArea() != null) {
            predicates.add(cb.lessThanOrEqualTo(farmRoot.get("area"), criteria.maxArea()));
        }

        if (criteria.creationDateAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farmRoot.get("creationDate"), criteria.creationDateAfter()));
        }

        if (criteria.creationDateBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(farmRoot.get("creationDate"), criteria.creationDateBefore()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Farm> typedQuery = entityManager.createQuery(query);
        List<Farm> farms = typedQuery.getResultList();

        return farms.stream()
                .map(mapper::toDto)
                .toList();
    }
}
