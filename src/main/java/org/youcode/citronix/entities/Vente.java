package org.youcode.citronix.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @NotNull
    private LocalDate dateVente;

    @NotNull
    private double prixUnitaire;

    @NotNull
    private double quantite;

    @NotBlank
    private String client;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;

}
