package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;

public record DetailRecolteRequestDTO(
        @NotNull
        double quantite,

        @NotNull
        Long arbre,

        @NotNull
        Long recolte
) {
}
