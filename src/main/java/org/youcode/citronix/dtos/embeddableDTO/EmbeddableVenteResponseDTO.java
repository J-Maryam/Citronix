package org.youcode.citronix.dtos.embeddableDTO;

import org.youcode.citronix.entities.Recolte;
import java.time.LocalDate;

public record EmbeddableVenteResponseDTO(
        Long id,
        LocalDate dateVente,
        double prixUnitaire,
        double quantite,
        String client,
        Recolte recolte
) {
}
