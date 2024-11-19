package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ArbreRequestDTO(
        @PastOrPresent
        @NotNull
        LocalDate datePlantation,

        @NotNull
        Long champ
) {
}
