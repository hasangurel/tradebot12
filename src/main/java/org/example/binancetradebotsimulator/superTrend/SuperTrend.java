package org.example.binancetradebotsimulator.superTrend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SuperTrend {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int length;
    private int minMult;
    private int maxMult;
    private double step;
    private double perfAlpha;
    private String fromCluster;
    private int maxIter;
    private int maxData;
    private boolean showGradient;
    private boolean showSignals;
    private boolean showDash;
    private String dashLoc;
    private String textSize;

    private static class SuperTrend {
        double upper;
        double lower;
        double output;
        double perf;
        double factor;
        int trend;

        SuperTrend() {
            this.upper = 0;
            this.lower = 0;
            this.output = 0;
            this.perf = 0;
            this.factor = 0;
            this.trend = 0;
        }
    }

    private static class Vector {
        List<Double> out;

        Vector() {
            this.out = new ArrayList<>();
        }
    }
}
