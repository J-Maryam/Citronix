package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Field;

import java.time.LocalDate;

public record TreeRequestDTO(
        @PastOrPresent
        @NotNull
        LocalDate plantingDate,

        @NotNull
        @Exists(entityClass = Field.class, message = "Field ID does not exist")
        Long field
) {
}
