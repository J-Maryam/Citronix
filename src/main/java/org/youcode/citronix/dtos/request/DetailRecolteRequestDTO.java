package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Arbre;
import org.youcode.citronix.entities.DetailRecolte;
import org.youcode.citronix.entities.Ferme;
import org.youcode.citronix.entities.Recolte;

public record DetailRecolteRequestDTO(
        @NotNull
        double quantite,

        @NotNull
        @Exists(entityClass = Arbre.class, message = "Arbre id does not exists")
        Long arbre,

        @NotNull
        @Exists(entityClass = Recolte.class, message = "Recolte id does not exists")
        Long recolte
) {
}
