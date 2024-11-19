package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Farm;

public record ChampRequestDTO(
        @NotNull
        double superficie,

        @NotNull
        @Exists(entityClass = Farm.class, message = "Farm id does not exists")
        Long ferme
) {
}
