package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.FermeRequestDTO;
import org.youcode.citronix.dtos.response.FermeResponseDTO;
import org.youcode.citronix.entities.Ferme;
import org.youcode.citronix.services.FermeService;

@Service
@Transactional
@Validated
public class FermeServiceImpl extends GenericServiceImpl<Ferme, Long, FermeRequestDTO, FermeResponseDTO> implements FermeService {
}
