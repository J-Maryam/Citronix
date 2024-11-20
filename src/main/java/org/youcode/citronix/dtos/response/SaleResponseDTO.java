package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableHarvestResponseDTO;

import java.time.LocalDate;

public record SaleResponseDTO(
        Long id,
        LocalDate saleDate,
        double unitPrice,
        double quantity,
        String client,
        EmbeddableHarvestResponseDTO harvest
) {
}
