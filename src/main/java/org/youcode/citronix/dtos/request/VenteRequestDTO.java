package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Harvest;

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
        @Exists(entityClass = Harvest.class, message = "Recolte id does not exists")
        Long recolte
) {
}
