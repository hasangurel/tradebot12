package org.example.binancetradebotsimulator.stockData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface StockDataRepository extends JpaRepository<StockData,String> {
    Optional<StockData> findByCloseTime(Date closeTime);
}
