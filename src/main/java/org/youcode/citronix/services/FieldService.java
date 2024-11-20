package org.youcode.citronix.services;

import org.youcode.citronix.common.services.GenericService;
import org.youcode.citronix.dtos.request.FieldRequestDTO;
import org.youcode.citronix.dtos.response.FieldResponseDTO;
import org.youcode.citronix.entities.Field;

public interface FieldService extends GenericService<Field, Long, FieldRequestDTO, FieldResponseDTO> {
}
