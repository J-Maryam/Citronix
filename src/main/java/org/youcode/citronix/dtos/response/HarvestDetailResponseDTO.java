package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableTreeResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableHarvestResponseDTO;

public record HarvestDetailResponseDTO(
        double quantity,
        EmbeddableTreeResponseDTO tree,
        EmbeddableHarvestResponseDTO harvest
) {
}
