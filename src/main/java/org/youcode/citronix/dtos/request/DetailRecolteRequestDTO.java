package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.entities.Harvest;

public record DetailRecolteRequestDTO(
        @NotNull
        double quantite,

        @NotNull
        @Exists(entityClass = Tree.class, message = "Arbre id does not exists")
        Long arbre,

        @NotNull
        @Exists(entityClass = Harvest.class, message = "Recolte id does not exists")
        Long recolte
) {
}
