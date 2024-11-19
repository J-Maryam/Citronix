package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

        @NotNull
        Long champ
) {
}
