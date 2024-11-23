package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.UniqueValue;
import org.youcode.citronix.entities.Farm;

import java.time.LocalDate;

public record FarmRequestDTO(
        @NotBlank(message = "The farm name must not be empty.")
        String name,

        @NotBlank(message = "The farm location must not be empty.")
        @UniqueValue(entityClass = Farm.class, fieldName = "location", message = "Farm localisation already exists")
        String location,

        @NotNull(message = "The farm area must not be null.")
        double area,

        @NotNull(message = "The creation date must not be null.")
        @PastOrPresent
        LocalDate creationDate

) {
}
