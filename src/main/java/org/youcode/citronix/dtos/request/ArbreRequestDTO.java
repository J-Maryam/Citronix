package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Field;

import java.time.LocalDate;

public record ArbreRequestDTO(
        @PastOrPresent
        @NotNull
        LocalDate datePlantation,

        @NotNull
        @Exists(entityClass = Field.class, message = "Champ id does not exists")
        Long champ
) {
}
