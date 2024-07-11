package org.example.binancetradebotsimulator.stockData;

public record StockDataCreateRequest(
        String symbol,
        String interval
) {

}
