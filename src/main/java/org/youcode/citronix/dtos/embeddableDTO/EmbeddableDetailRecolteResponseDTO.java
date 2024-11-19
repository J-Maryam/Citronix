package org.youcode.citronix.dtos.embeddableDTO;

import org.youcode.citronix.entities.Arbre;
import org.youcode.citronix.entities.Recolte;

public record EmbeddableDetailRecolteResponseDTO(
        double quantite,
        Arbre arbre,
        Recolte recolte
) {
}
