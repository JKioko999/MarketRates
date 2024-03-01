package com.Treasury.MarketRates.CalculationDTO.CustomerCalculation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBondsCouponCalc {

    private Long faceValue;
    private Double couponRate;
    private Double period;
}
