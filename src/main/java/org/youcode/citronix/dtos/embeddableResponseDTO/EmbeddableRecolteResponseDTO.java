package org.youcode.citronix.dtos.embeddableResponseDTO;

import org.youcode.citronix.entities.enums.Saison;

import java.time.LocalDate;

public record EmbeddableRecolteResponseDTO(
        Long id,
        Saison saison,
        LocalDate dateRecolte,
        double quantiteTotale,
        EmbeddableChampResponseDTO champ
) {
}
