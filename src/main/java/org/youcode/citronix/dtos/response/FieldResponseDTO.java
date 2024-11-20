package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableTreeResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableFarmResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableHarvestResponseDTO;
import java.util.List;

public record FieldResponseDTO(
        Long id,
        double area,
        List<EmbeddableTreeResponseDTO> trees,
        List<EmbeddableHarvestResponseDTO> harvests
) {
}
