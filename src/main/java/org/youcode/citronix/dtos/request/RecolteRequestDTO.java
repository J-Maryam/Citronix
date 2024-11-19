package org.youcode.citronix.dtos.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.entities.Champ;
import org.youcode.citronix.entities.enums.Saison;

import java.time.LocalDate;

public record RecolteRequestDTO(

        @NotNull
        Saison saison,

        @NotNull
        @PastOrPresent
        LocalDate dateRecolte,

        @NotNull
        double quantiteTotale,

        @ManyToOne
        @JoinColumn(name = "champ_id", nullable = false)
        Champ champ
) {
}
