package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;

public record ChampRequestDTO(
        @NotNull
        double superficie,

        @NotNull
        Long ferme
) {
}
