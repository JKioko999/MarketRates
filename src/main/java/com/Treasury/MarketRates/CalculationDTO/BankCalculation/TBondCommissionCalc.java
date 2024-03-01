package com.Treasury.MarketRates.CalculationDTO.BankCalculation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBondCommissionCalc {
    private Long faceValue;
    private Double commissionRate;
}
