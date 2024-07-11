package org.example.binancetradebotsimulator.kama;

import lombok.Builder;

import java.util.Date;

@Builder
public record KAMATradeResponseDTO(
        String symbol,
        Double profit,
        Date closetime,
        Double capital

) {
}
