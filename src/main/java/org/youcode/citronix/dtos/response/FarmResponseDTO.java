package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableFieldResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record FarmResponseDTO(
        Long id,
        String name,
        String location,
        double area,
        LocalDate creationDate,
        List<EmbeddableFieldResponseDTO> fields
) {
}
