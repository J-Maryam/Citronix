package org.youcode.citronix.dtos.embeddableResponseDTO;

public record EmbeddableHarvestDetailResponseDTO(
        double quantity,
        EmbeddableTreeResponseDTO tree,
        EmbeddableHarvestResponseDTO harvest
) {
}
