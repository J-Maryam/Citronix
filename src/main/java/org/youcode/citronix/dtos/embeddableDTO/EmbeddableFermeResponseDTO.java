package org.youcode.citronix.dtos.embeddableDTO;

import org.youcode.citronix.entities.Champ;

import java.time.LocalDate;
import java.util.List;

public record EmbeddableFermeResponseDTO(
        Long id,
        String nom,
        String localisation,
        double superficie,
        LocalDate dateCreation
) {
}
