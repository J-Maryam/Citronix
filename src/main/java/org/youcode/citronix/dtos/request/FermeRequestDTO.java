package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record FermeRequestDTO(
        @NotBlank
        //add unique value annotation
        String nom,

        @NotBlank
        String localisation,

        @NotNull
        double superficie,

        @NotNull
        @PastOrPresent
        LocalDate dateCreation

) {
}
