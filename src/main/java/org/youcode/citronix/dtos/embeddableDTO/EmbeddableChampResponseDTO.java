package org.youcode.citronix.dtos.embeddableDTO;

import org.youcode.citronix.dtos.response.FermeResponseDTO;

public record EmbeddableChampResponseDTO(
        Long id,
        double superficie,
        FermeResponseDTO ferme
) {
}
