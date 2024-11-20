package org.youcode.citronix.dtos.embeddableResponseDTO;

import java.time.LocalDate;

public record EmbeddableTreeResponseDTO(
        Long id,
        LocalDate plantingDate,
        EmbeddableFieldResponseDTO field
) {
}
