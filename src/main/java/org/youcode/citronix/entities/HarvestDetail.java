package org.youcode.citronix.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.youcode.citronix.entities.embeddable.HarvestDetailId;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class HarvestDetail {

    @EmbeddedId
    private HarvestDetailId id;

    @NotNull
    private double quantity;

    @ManyToOne
    @MapsId("treeId")
    @JoinColumn(name = "tree_id")
    private Tree tree;

    @ManyToOne
    @MapsId("harvestId")
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;
}
