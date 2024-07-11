package org.example.binancetradebotsimulator.stockData;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockData")
@RequiredArgsConstructor
public class StockDataController {
    private final StockDataService stockDataService;

    @PostMapping("createData")
    public ResponseEntity<Void> createData(@RequestBody StockDataCreateRequest request) {
        return new ResponseEntity<>(stockDataService.saveStockData(request), HttpStatus.OK);
    }
}
