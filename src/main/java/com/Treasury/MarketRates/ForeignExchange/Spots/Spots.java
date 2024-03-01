package com.Treasury.MarketRates.ForeignExchange.Spots;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Spots")
public class Spots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spotId;

    private Double bidPrice;

    private Double askPrice;
}
