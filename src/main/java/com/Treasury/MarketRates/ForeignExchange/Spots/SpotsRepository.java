package com.Treasury.MarketRates.ForeignExchange.Spots;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotsRepository extends JpaRepository<Spots,Integer> {
}
