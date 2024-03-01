package com.Treasury.MarketRates.CalculationDTO.BankCalculation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanInterestCalc {

    private Double principal;
    private Double annualRate;
    private Integer period;
}
