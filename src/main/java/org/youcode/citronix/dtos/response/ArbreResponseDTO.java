package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableChampResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableDetailRecolteResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record ArbreResponseDTO(
        Long id,
        LocalDate datePlantation,
        EmbeddableChampResponseDTO champ,
        List<EmbeddableDetailRecolteResponseDTO> detailRecoltes
) {
}
