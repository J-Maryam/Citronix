package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record VenteRequestDTO(

        @PastOrPresent
        LocalDate dateVente,

        @NotNull
        double prixUnitaire,

        @NotNull
        double quantite,

        @NotBlank
        String client,

        @NotNull
        Long recolte
) {
}
