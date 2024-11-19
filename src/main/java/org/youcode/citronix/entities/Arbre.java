package org.youcode.citronix.entities;

import jakarta.persistence.*;
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
public class Arbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @NotNull
    private LocalDate datePlantation;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "arbre")
    private List<DetailRecolte> detailRecoltes = new ArrayList<>();
}
