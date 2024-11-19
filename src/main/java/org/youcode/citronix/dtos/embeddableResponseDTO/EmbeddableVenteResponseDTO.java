package org.youcode.citronix.dtos.embeddableResponseDTO;

import java.time.LocalDate;

public record EmbeddableVenteResponseDTO(
        Long id,
        LocalDate dateVente,
        double prixUnitaire,
        double quantite,
        String client,
        EmbeddableRecolteResponseDTO recolte
) {
}
