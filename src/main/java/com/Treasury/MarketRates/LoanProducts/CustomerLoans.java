package com.Treasury.MarketRates.LoanProducts;


import com.Treasury.MarketRates.LoanProducts.EntityEnum.LoanCode;
import com.Treasury.MarketRates.LoanProducts.EntityEnum.LoanProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Loan Products")
public class CustomerLoans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LoanId;

    @Enumerated(EnumType.STRING)
    private LoanCode loanCode;

    private Long principal;

    private Integer annualRate;

    private Integer period;

    @Enumerated(EnumType.STRING)
    private LoanProduct loanProduct;
}
