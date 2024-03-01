package com.Treasury.MarketRates.LoanProducts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLoanRepo extends JpaRepository<CustomerLoans, Long>{
}
