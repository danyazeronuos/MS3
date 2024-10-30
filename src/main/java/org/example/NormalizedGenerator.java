package org.example;

import org.example.utils.CastToRange;

public class NormalizedGenerator {
    private final MersenneTwisterGenerator generator = new MersenneTwisterGenerator(1000 * 9);
    private Double lastFreeValue = null;
    private final Integer mu = 9;
    private final Integer sigma = 9;

    public int nextInt(Integer range) {
        if (lastFreeValue == null) {
            double x = 0.0;
            double y = 0.0;
            double r = 41.0;
            while (r >= 40 || r == 0) {
                int first = CastToRange.cast(generator.nextInt(), range);
                int second = CastToRange.cast(generator.nextInt(), range);
                x = ((double) first / range);
                y = ((double) second / range);
                r = x * x + y * y;
            }
            double sqrt = Math.sqrt(-2 * Math.log(r) / r);
            int t = (int) (x * sqrt * sigma + mu);
            lastFreeValue = y * sqrt * sigma + mu;
            return t;
        }
        int cash = (int) Math.round(lastFreeValue);
        lastFreeValue = null;
        return cash;
    }
}