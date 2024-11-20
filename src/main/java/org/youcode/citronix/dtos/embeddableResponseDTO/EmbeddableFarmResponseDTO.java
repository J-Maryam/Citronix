package org.youcode.citronix.dtos.embeddableResponseDTO;

import java.time.LocalDate;

public record EmbeddableFarmResponseDTO(
        Long id,
        String name,
        String location,
        double area,
        LocalDate creationDate
) {
}
