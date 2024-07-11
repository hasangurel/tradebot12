package org.example.binancetradebotsimulator.stockData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String symbol;

    private Double closePrice;

    private Date closeTime;
}
