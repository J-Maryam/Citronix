package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Field;

import java.time.LocalDate;

public record TreeRequestDTO(
        @PastOrPresent(message = "The planting date must be in the past or present.")
        @NotNull(message = "The planting date must not be null.")
        LocalDate plantingDate,

        @NotNull(message = "The field ID must not be null.")
        @Exists(entityClass = Field.class, message = "Field ID does not exist")
        Long fieldId
) {
}
