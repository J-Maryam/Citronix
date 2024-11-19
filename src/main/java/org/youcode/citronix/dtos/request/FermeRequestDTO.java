package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.UniqueValue;
import org.youcode.citronix.entities.Ferme;

import java.time.LocalDate;

public record FermeRequestDTO(
        @NotBlank
        String nom,

        @NotBlank
        @UniqueValue(entityClass = Ferme.class, fieldName = "localisation", message = "Farmer localisation already exists")
        String localisation,

        @NotNull
        double superficie,

        @NotNull
        @PastOrPresent
        LocalDate dateCreation

) {
}
