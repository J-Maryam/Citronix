package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Arbre;
import org.youcode.citronix.entities.Ferme;

import java.time.LocalDate;

public record ArbreRequestDTO(
        @PastOrPresent
        @NotNull
        LocalDate datePlantation,

        @NotNull
        @Exists(entityClass = Arbre.class, message = "Arbre id does not exists")
        Long champ
) {
}
