package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableDTO.EmbeddableArbreResponseDTO;
import org.youcode.citronix.dtos.embeddableDTO.EmbeddableRecolteResponseDTO;
import java.util.List;

public record ChampResponseDTO(
        Long id,
        double superficie,
        FermeResponseDTO ferme,
        List<EmbeddableArbreResponseDTO> arbres,
        List<EmbeddableRecolteResponseDTO> recoltes
) {
}
