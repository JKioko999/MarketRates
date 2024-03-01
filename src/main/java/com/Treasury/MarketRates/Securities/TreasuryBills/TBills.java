package com.Treasury.MarketRates.Securities.TreasuryBills;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBills")
public class TBills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TBillId;

    private Long faceValue;

    private Double commissionRate;

    private String issuanceDate;

    private String maturityDate;

    @Enumerated(EnumType.STRING)
    private Year_Period yearPeriod;
}
