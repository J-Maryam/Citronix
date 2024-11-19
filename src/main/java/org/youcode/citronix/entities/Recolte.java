package org.youcode.citronix.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.entities.enums.Saison;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;

    @NotNull
    @PastOrPresent
    private LocalDate dateRecolte;

    @NotNull
    private double quantiteTotale;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

//    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
//    private List<DetailRecolte> detailsRecolte = new ArrayList<>();

}
