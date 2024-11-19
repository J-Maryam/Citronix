package org.youcode.citronix.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nom;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    private double superficie;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "ferme")
    private List<Champ> champs = new ArrayList<>();
}
