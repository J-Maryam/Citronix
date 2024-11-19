package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Harvest;

import java.time.LocalDate;

public record SaleRequestDTO(

        @PastOrPresent
        LocalDate saleDate,

        @NotNull
        double unitPrice,

        @NotNull
        double quantity,

        @NotBlank
        String client,

        @NotNull
        @Exists(entityClass = Harvest.class, message = "Harvest ID does not exist")
        Long harvest
) {
}
