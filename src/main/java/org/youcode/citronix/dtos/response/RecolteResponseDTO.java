package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableDTO.EmbeddableChampResponseDTO;
import org.youcode.citronix.dtos.embeddableDTO.EmbeddableDetailRecolteResponseDTO;
import org.youcode.citronix.entities.enums.Saison;

import java.time.LocalDate;
import java.util.List;

public record RecolteResponseDTO(
        Long id,
        Saison saison,
        LocalDate dateRecolte,
        double quantiteTotale,
        EmbeddableChampResponseDTO champ,
        List<EmbeddableDetailRecolteResponseDTO> detailRecoltes
) {
}
