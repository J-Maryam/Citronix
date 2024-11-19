package org.youcode.citronix.dtos.embeddableDTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.dtos.response.FermeResponseDTO;
import org.youcode.citronix.entities.Champ;
import org.youcode.citronix.entities.enums.Saison;

import java.time.LocalDate;

public record EmbeddableRecolteResponseDTO(
        Long id,
        Saison saison,
        LocalDate dateRecolte,
        double quantiteTotale,
        Champ champ
) {
}
