package com.Treasury.MarketRates.Securities.TreasuryBills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TBillsRepo extends JpaRepository<TBills, Long>{
}
