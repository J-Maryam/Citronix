package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableDTO.EmbeddableArbreResponseDTO;
import org.youcode.citronix.dtos.embeddableDTO.EmbeddableRecolteResponseDTO;

public record DetailRecolteResponseDTO(
        double quantite,
        EmbeddableArbreResponseDTO arbre,
        EmbeddableRecolteResponseDTO recolte
) {
}
