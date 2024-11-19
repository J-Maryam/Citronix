package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableArbreResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableRecolteResponseDTO;

public record DetailRecolteResponseDTO(
        double quantite,
        EmbeddableArbreResponseDTO arbre,
        EmbeddableRecolteResponseDTO recolte
) {
}
