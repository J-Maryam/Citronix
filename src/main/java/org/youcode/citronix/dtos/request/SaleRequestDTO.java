package org.youcode.citronix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.youcode.citronix.common.validation.annotation.Exists;
import org.youcode.citronix.entities.Harvest;

import java.time.LocalDate;

public record SaleRequestDTO(

        @PastOrPresent(message = "The sale date must be in the past or present.")
        LocalDate saleDate,

        @NotNull(message = "The unit price must not be null.")
        double unitPrice,

        @NotNull(message = "The quantity must not be null.")
        double quantity,

        @NotBlank(message = "The client must not be empty.")
        String client,

        @NotNull(message = "The harvest ID must not be null.")
        @Exists(entityClass = Harvest.class, message = "Harvest ID does not exist")
        Long harvestId
) {
}
