package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.entities.enums.Season;

import java.time.LocalDate;

public record HarvestRequestDTO(

        Season season,

        @NotNull(message = "The harvest date must not be null.")
        @PastOrPresent(message = "The harvest date must be in the past or present.")
        LocalDate harvestDate,

        @NotNull(message = "The total quantity must not be null.")
        double totalQuantity,

        @NotNull(message = "The field ID must not be null.")
        @Exists(entityClass = Field.class, message = "Field ID does not exist")
        Long fieldId
) {
}
