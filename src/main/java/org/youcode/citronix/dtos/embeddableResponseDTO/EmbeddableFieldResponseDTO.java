package org.youcode.citronix.dtos.embeddableResponseDTO;

public record EmbeddableFieldResponseDTO(
        Long id,
        double area,
        EmbeddableFarmResponseDTO farm
) {
}
