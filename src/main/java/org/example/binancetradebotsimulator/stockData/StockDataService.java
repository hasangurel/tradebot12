package org.example.binancetradebotsimulator.stockData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockDataService {

    private final StockDataRepository stockDataRepository;
    private final StockDataUtilsBinance stockDataUtilsBinance;

    public Void saveStockData(StockDataCreateRequest request) {
        List<StockData> stockDataList=StockDataUtilsBinance.getStockData(request.symbol(), request.interval(),"200000");
        for (StockData stockData : stockDataList) {
            Optional<StockData> existingData = stockDataRepository.findByCloseTime(stockData.getCloseTime());
            if (existingData.isEmpty()) {
                stockDataRepository.save(stockData);
            }

       }return null;
    }
}