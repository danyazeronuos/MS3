package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NormalizedGeneratorTest {
    private NormalizedGenerator normalizedGenerator;

    @BeforeEach
    void setUp() {
        normalizedGenerator = new NormalizedGenerator();
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 10_000, 100_000, 1_000_000})
    public void mersenneTwisterGenerator_2(int interval) {
        int[] counts = new int[10];

        for (int i = 0; i < interval; i++) {
            int value = normalizedGenerator.nextInt(100);
            counts[value / 10]++;
        }

        double chiSquare = 0;
        double expected = interval / 10.0;

        for (int count : counts) {
            chiSquare += Math.pow(count - expected, 2) / expected;
        }

        final double res = chiSquare;
        double criticalValue = 16.919;

        Assertions.assertFalse(chiSquare > criticalValue,
                () -> "Value %f (X²) > %f (constant)".formatted(res, criticalValue));
    }
}