package org.youcode.citronix.dtos.embeddableResponseDTO;

public record EmbeddableChampResponseDTO(
        Long id,
        double superficie,
        EmbeddableFermeResponseDTO ferme
) {
}
