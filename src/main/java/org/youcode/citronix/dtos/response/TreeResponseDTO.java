package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableFieldResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableHarvestDetailResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record TreeResponseDTO(
        Long id,
        LocalDate plantingDate,
        EmbeddableFieldResponseDTO field,
        List<EmbeddableHarvestDetailResponseDTO> harvestDetails
) {
}
