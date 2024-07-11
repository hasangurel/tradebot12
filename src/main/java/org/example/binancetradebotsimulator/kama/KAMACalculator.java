package org.example.binancetradebotsimulator.kama;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Data
public class KAMACalculator {

    private static final int SLOWEST = 30;
    private static final int FASTEST = 2;
    private static final int PERIOD = 20;
    private static final double FASTEST_SC = 2.0 / (FASTEST + 1);
    private static final double SLOWEST_SC = 2.0 / (SLOWEST + 1);

    public boolean getBuySignal(List<Double> prices) {
        if (prices.size() < PERIOD + 1) {
            throw new IllegalArgumentException("Not enough data to perform the calculation.");
        }

        double[] kama = calculateKAMA(prices);

        int lastIndex = prices.size() - 1;
        int prevIndex = lastIndex - 1;

        double lastClose = prices.get(lastIndex);
        double prevClose = prices.get(prevIndex);
        double lastKAMA = kama[lastIndex];
        double prevKAMA = kama[prevIndex];

        return lastClose > lastKAMA && !(prevClose > prevKAMA);
    }

    public boolean getSellSignal(List<Double> prices) {
        if (prices.size() < PERIOD + 1) {
            throw new IllegalArgumentException("Not enough data to perform the calculation.");
        }

        double[] kama = calculateKAMA(prices);

        int lastIndex = prices.size() - 1;
        int prevIndex = lastIndex - 1;

        double lastClose = prices.get(lastIndex);
        double prevClose = prices.get(prevIndex);
        double lastKAMA = kama[lastIndex];
        double prevKAMA = kama[prevIndex];

        return lastClose < lastKAMA && !(prevClose < prevKAMA);
    }

    private double[] calculateER(List<Double> prices) {
        double[] er = new double[prices.size()];
        for (int i = PERIOD; i < prices.size(); i++) {
            double change = Math.abs(prices.get(i) - prices.get(i - PERIOD));
            double volatility = 0.0;
            for (int j = i - PERIOD + 1; j <= i; j++) {
                volatility += Math.abs(prices.get(j) - prices.get(j - 1));
            }
            er[i] = change / volatility;
        }
        return er;
    }

    private double[] calculateSC(double[] er) {
        double[] sc = new double[er.length];
        for (int i = PERIOD; i < er.length; i++) {
            sc[i] = Math.pow((er[i] * (FASTEST_SC - SLOWEST_SC) + SLOWEST_SC), 2);
        }
        return sc;
    }

    private double[] calculateKAMA(List<Double> prices) {
        double[] er = calculateER(prices);
        double[] sc = calculateSC(er);
        double[] kama = new double[prices.size()];
        kama[PERIOD] = prices.get(PERIOD);
        for (int i = PERIOD + 1; i < prices.size(); i++) {
            kama[i] = kama[i - 1] + sc[i] * (prices.get(i) - kama[i - 1]);
        }
        return kama;
    }
}
