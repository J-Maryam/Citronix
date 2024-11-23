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
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @NotNull
    private LocalDate saleDate;

    @NotNull
    private double unitPrice;

    @NotNull
    private double quantity;

    @NotBlank
    private String client;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    public double getRevenue() {
        return quantity * unitPrice;
    }
}
