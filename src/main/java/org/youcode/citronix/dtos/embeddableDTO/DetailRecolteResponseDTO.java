package org.youcode.citronix.dtos.embeddableDTO;

public record DetailRecolteResponseDTO(
        double quantite,
        EmbeddableArbreResponseDTO arbre,
        EmbeddableRecolteResponseDTO recolte
) {
}
