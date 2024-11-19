package org.youcode.citronix.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record HarvestDetailId(
        @Column(name = "tree_id") Long treeId,
        @Column(name = "harvest_id") Long harvestId
) implements Serializable {
}
