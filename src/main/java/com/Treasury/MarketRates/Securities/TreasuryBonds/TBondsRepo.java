package com.Treasury.MarketRates.Securities.TreasuryBonds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TBondsRepo extends JpaRepository<TBonds, Long>{
}
