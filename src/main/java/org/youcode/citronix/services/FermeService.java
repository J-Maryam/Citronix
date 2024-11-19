package org.youcode.citronix.services;

import org.youcode.citronix.common.services.GenericService;
import org.youcode.citronix.dtos.request.FermeRequestDTO;
import org.youcode.citronix.dtos.response.FermeResponseDTO;
import org.youcode.citronix.entities.Ferme;

public interface FermeService extends GenericService<Ferme, Long, FermeRequestDTO, FermeResponseDTO> {
}
