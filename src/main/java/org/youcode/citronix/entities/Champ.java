package org.youcode.citronix.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    private double superficie; // en hectares

    @Column(nullable = false)
    private LocalDate dateCreation;


}
