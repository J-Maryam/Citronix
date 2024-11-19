package org.youcode.citronix.dtos.response;

import org.youcode.citronix.entities.Champ;

import java.time.LocalDate;
import java.util.List;

public record FermeResponseDTO(
        Long id,
        String nom,
        String localisation,
        double superficie,
        LocalDate dateCreation,
        List<Champ> champs
) {
}
