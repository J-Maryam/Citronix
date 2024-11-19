package org.youcode.citronix.dtos.response;

import org.youcode.citronix.dtos.embeddableDTO.EmbeddableRecolteResponseDTO;
import org.youcode.citronix.entities.Recolte;

import java.time.LocalDate;

public record VenteResponseDTO(
        Long id,
        LocalDate dateVente,
        double prixUnitaire,
        double quantite,
        String client,
        EmbeddableRecolteResponseDTO recolte
) {
}
