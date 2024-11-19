package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableArbreResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableFermeResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableRecolteResponseDTO;
import java.util.List;

public record ChampResponseDTO(
        Long id,
        double superficie,
        EmbeddableFermeResponseDTO ferme,
        List<EmbeddableArbreResponseDTO> arbres,
        List<EmbeddableRecolteResponseDTO> recoltes
) {
}
