package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.UniqueValue;
import org.youcode.citronix.entities.Ferme;

import java.time.LocalDate;

public record FermeRequestDTO(
        @NotBlank
        @UniqueValue(entityClass = Ferme.class, fieldName = "nom", message = "Farmer name already exists")
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
