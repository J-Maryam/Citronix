package org.youcode.citronix.dtos.embeddableDTO;

import org.youcode.citronix.entities.Champ;
import java.time.LocalDate;

public record EmbeddableArbreResponseDTO(
        Long id,
        LocalDate datePlantation,
        Champ champ
) {
}
