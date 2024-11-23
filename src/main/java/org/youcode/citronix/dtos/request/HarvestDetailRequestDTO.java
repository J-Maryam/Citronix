package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.entities.Harvest;

public record HarvestDetailRequestDTO(
        @NotNull(message = "The quantity must not be null.")
        double quantity,

        @NotNull(message = "The tree ID must not be null.")
        @Exists(entityClass = Tree.class, message = "Tree ID does not exist")
        Long treeId,

        @NotNull(message = "The harvest ID must not be null.")
        @Exists(entityClass = Harvest.class, message = "Harvest ID does not exist")
        Long harvestId
) {
}
