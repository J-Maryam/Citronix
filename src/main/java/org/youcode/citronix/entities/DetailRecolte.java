package org.youcode.citronix.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.youcode.citronix.entities.embeddable.DetailRecoltId;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class DetailRecolte {

    @EmbeddedId
    private DetailRecoltId id;

    @NotNull
    private double quantite;

    @ManyToOne
    @MapsId("arbreId")
    @JoinColumn(name = "arbre_id")
    private Arbre arbre;

    @ManyToOne
    @MapsId("recolteId")
    @JoinColumn(name = "recolte_id")
    private Recolte recolte;
}
