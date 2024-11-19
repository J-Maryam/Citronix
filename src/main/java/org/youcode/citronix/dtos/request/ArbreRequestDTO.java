package org.youcode.citronix.dtos.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.entities.Champ;

import java.time.LocalDate;

public record ArbreRequestDTO(
        @PastOrPresent
        @NotNull
        LocalDate datePlantation,

        @ManyToOne
        @JoinColumn(name = "champ_id", nullable = false)
        Champ champ
) {
}
