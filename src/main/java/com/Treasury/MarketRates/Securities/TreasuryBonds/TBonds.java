package com.Treasury.MarketRates.Securities.TreasuryBonds;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBonds")
public class TBonds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TBondId;

    private Long faceValue;

    private Double couponRate;

    private String issuanceDate;

    private String maturityDate;

    private Long Period;
}
