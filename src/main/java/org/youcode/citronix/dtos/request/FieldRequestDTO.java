package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Farm;

public record FieldRequestDTO(
        @NotNull
        double area,

        @NotNull
        @Exists(entityClass = Farm.class, message = "Farm ID does not exist")
        Long farmId
) {
}
