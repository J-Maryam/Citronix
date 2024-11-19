package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.entities.Harvest;

public record HarvestDetailRequestDTO(
        @NotNull
        double quantity,

        @NotNull
        @Exists(entityClass = Tree.class, message = "Tree ID does not exist")
        Long tree,

        @NotNull
        @Exists(entityClass = Harvest.class, message = "Harvest ID does not exist")
        Long harvest
) {
}
