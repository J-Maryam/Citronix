package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableFieldResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableHarvestDetailResponseDTO;
import org.youcode.citronix.entities.enums.Season;

import java.time.LocalDate;
import java.util.List;

public record HarvestResponseDTO(
        Long id,
        Season season,
        LocalDate harvestDate,
        double totalQuantity,
        EmbeddableFieldResponseDTO field,
        List<EmbeddableHarvestDetailResponseDTO> harvestDetails
) {
}
