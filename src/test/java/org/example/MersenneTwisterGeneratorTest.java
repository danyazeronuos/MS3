package org.example;

import org.example.utils.CastToRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

class MersenneTwisterGeneratorTest {
    private MersenneTwisterGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new MersenneTwisterGenerator(1000*9);
    }

    @ParameterizedTest
    @CsvSource({
            "4839283,2000",
            "293829382,1000",
            "-28392,1000",
            "0,100",
            "1000,2000"
    })
    public void castIntegerToRange(int integer, int range) {
        int castedToRange = CastToRange.cast(integer, range);
        Assertions.assertTrue(castedToRange >= 0);
        Assertions.assertTrue(castedToRange <= range);
    }

    @ParameterizedTest
    @ValueSource(ints = {20_000, 30_000, 50_000, 80_000, 100_000,
            150_000, 200_000, 250_000, 300_000, 400_000, 500_000, 1_000_000})
    public void mtg_CorrelationTest(int interval) {

        int previousValue = generator.nextInt();
        boolean hasCorrelation = false;

        for (int i = 1; i < interval; i++) {
            int value = generator.nextInt();
            if (value == previousValue) {
                hasCorrelation = true;
                break;
            }
            previousValue = value;
        }

        Assertions.assertFalse(hasCorrelation);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 1_500, 2_000, 5_000, 8_000, 10_000, 15_000,
            20_000, 30_000, 50_000, 80_000, 100_000, 150_000, 200_000, 250_000, 300_000, 400_000, 500_000, 1_000_000})
    public void mtg_PeriodTest(int interval) {
        Set<Integer> generated = new HashSet<>();

        var repeatedTimes = 0;
        for (int i = 0; i < interval; i++) {
            var currentGeneratedValue = generator.nextInt();
            if (generated.contains(currentGeneratedValue)) repeatedTimes++;
            generated.add(currentGeneratedValue);
        }

        Assertions.assertEquals(0, repeatedTimes);
    }
    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 10_000, 100_000, 1_000_000})
    public void mersenneTwisterGenerator_2(int interval) {
        int[] counts = new int[10];

        for (int i = 0; i < interval; i++) {
            int value = generator.nextInt(100);
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