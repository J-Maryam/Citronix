package org.youcode.citronix.dtos.embeddableResponseDTO;

import java.time.LocalDate;

public record EmbeddableArbreResponseDTO(
        Long id,
        LocalDate datePlantation,
        EmbeddableChampResponseDTO champ
) {
}
