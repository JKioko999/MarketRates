package com.Treasury.MarketRates.CalculationDTO.BankCalculation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBillCommissionCalc {

    private Double commissionRate;
    private Double faceValue;
}
