package org.youcode.citronix.dtos.embeddableResponseDTO;

import org.youcode.citronix.entities.enums.Season;

import java.time.LocalDate;

public record EmbeddableHarvestResponseDTO(
        Long id,
        Season season,
        LocalDate harvestDate,
        double totalQuantity,
        EmbeddableFieldResponseDTO field
) {
}
