package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableDTO.EmbeddableDetailRecolteResponseDTO;
import org.youcode.citronix.entities.Champ;

import java.time.LocalDate;
import java.util.List;

public record ArbreResponseDTO(
        Long id,
        LocalDate datePlantation,
        Champ champ,
        List<EmbeddableDetailRecolteResponseDTO>detailRecoltes
) {
}
