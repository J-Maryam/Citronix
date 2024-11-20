package org.youcode.citronix.dtos.embeddableResponseDTO;

import java.time.LocalDate;

public record EmbeddableSaleResponseDTO(
        Long id,
        LocalDate saleDate,
        double unitPrice,
        double quantity,
        String client,
        EmbeddableHarvestResponseDTO harvest
) {
}
