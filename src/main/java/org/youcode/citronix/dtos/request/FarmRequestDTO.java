package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.UniqueValue;
import org.youcode.citronix.entities.Farm;

import java.time.LocalDate;

public record FarmRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        @UniqueValue(entityClass = Farm.class, fieldName = "location", message = "Farm localisation already exists")
        String location,

        @NotNull
        double area,

        @NotNull
        @PastOrPresent
        LocalDate creationDate

) {
}
