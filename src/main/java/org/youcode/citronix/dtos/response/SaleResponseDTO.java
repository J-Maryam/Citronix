package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableHarvestResponseDTO;

import java.time.LocalDate;

public record SaleResponseDTO(
        Long id,
        LocalDate saleDate,
        double unitPrice,
        double quantity,
        double revenue,
        String client,
        EmbeddableHarvestResponseDTO harvest
) {
}
