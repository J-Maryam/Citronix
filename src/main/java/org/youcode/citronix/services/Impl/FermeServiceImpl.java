package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FermeResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.mappers.FermeMapper;
import org.youcode.citronix.repositories.FermeRepository;
import org.youcode.citronix.services.FermeService;

@Service
@Transactional
@Validated
public class FermeServiceImpl extends GenericServiceImpl<Farm, Long, FarmRequestDTO, FermeResponseDTO> implements FermeService {
        public FermeServiceImpl(FermeRepository repository, FermeMapper mapper) {
            super(repository, mapper);
        }

//    @Override
//    public FermeResponseDTO update(Long id, FermeRequestDTO requestDto) {
//        Farm existingFerme = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Entity with Id " + id + " not found"));
//
//
//        if (requestDto.nom() != null) {
//            existingFerme.setNom(requestDto.nom());
//        }
//        if (requestDto.localisation() != null) {
//            existingFerme.setLocalisation(requestDto.localisation());
//        }
//        if (requestDto.superficie() != 0) {
//            existingFerme.setSuperficie(requestDto.superficie());
//        }
//        if (requestDto.dateCreation() != null) {
//            existingFerme.setDateCreation(requestDto.dateCreation());
//        }
//
//        Farm updatedEntity = repository.save(existingFerme);
//        return mapper.toDto(updatedEntity);
//    }
}
