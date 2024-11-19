package org.youcode.citronix.dtos.embeddableResponseDTO;

public record EmbeddableDetailRecolteResponseDTO(
        double quantite,
        EmbeddableArbreResponseDTO arbre,
        EmbeddableRecolteResponseDTO recolte
) {
}
