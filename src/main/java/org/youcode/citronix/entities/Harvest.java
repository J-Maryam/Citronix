package org.youcode.citronix.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.youcode.citronix.entities.enums.Saison;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Harvest {

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
    private Field champ;

    @OneToMany(mappedBy = "recolte")
    private List<HarvestDetail> detailRecoltes = new ArrayList<>();

}
