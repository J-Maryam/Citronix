package org.youcode.citronix.dtos.embeddableResponseDTO;
import java.time.LocalDate;

public record EmbeddableFermeResponseDTO(
        Long id,
        String nom,
        String localisation,
        double superficie,
        LocalDate dateCreation
) {
}
