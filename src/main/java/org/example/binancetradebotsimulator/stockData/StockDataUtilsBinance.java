package org.example.binancetradebotsimulator.stockData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Component
@Slf4j
public class StockDataUtilsBinance {

    private static final String BASE_URL = "https://api.binance.com/api/v3/klines";

    public static List<StockData> getStockData(String symbol, String interval, String limit) {
        String url = buildUrl(symbol, interval, limit);
        List<StockData> stockDataList = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);

                    // JSON verisini işleme
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(result);

                    // Verileri ayarla
                    stockDataList = getStockDataList(rootNode, symbol);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stockDataList.get(1));
        return stockDataList;
    }

    private static String buildUrl(String symbol, String interval, String limit) {
        String encodedSymbol = encodeValue(symbol);
        String encodedInterval = encodeValue(interval);
        String encodedLimit = encodeValue(limit);
        return BASE_URL + "?symbol=" + encodedSymbol + "&interval=" + encodedInterval + "&limit=" + encodedLimit;
    }

    private static List<StockData> getStockDataList(JsonNode rootNode, String symbol) {
        List<StockData> stockDataList = new ArrayList<>();
        for (JsonNode kline : rootNode) {
            long closeTimeMillis = kline.get(6).asLong(); // 6. indeks kapanış zamanını içerir
            double closePrice = kline.get(4).asDouble(); // 4. indeks kapanış fiyatını içerir

            StockData stockData = StockData.builder()
                    .symbol(symbol)
                    .closePrice(closePrice)
                    .closeTime(new Date(closeTimeMillis))
                    .build();

            stockDataList.add(stockData);
        }
        return stockDataList;
    }

    private static String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
