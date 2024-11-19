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
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nom;

    @NotBlank
    private String localisation;

    @NotNull
    private double superficie;

    @NotNull
    @PastOrPresent
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "ferme")
    @NotNull
    private List<Champ> champs = new ArrayList<>();
}
