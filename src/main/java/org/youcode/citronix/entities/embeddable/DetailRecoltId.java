package org.youcode.citronix.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record DetailRecoltId(
        @Column(name = "arbre_id") Long arbreId,
        @Column(name = "recolt_id") Long recoltId
) implements Serializable {
}
