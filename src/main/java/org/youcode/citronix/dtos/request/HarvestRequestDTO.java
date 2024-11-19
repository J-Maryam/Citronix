package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.entities.enums.Saison;

import java.time.LocalDate;

public record HarvestRequestDTO(

        @NotNull
        Saison season,

        @NotNull
        @PastOrPresent
        LocalDate harvestDate,

        @NotNull
        double totalQuantity,

        @NotNull
        @Exists(entityClass = Field.class, message = "Field ID does not exist")
        Long field
) {
}
