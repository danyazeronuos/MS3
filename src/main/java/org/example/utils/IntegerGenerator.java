package org.example.utils;

import java.util.function.Function;

public class IntegerGenerator {
    public static Integer apply(Integer currentSequence) {
        currentSequence ^= (currentSequence >>> 11);
        currentSequence ^= (currentSequence << 7) & 0x9d2c5680;
        currentSequence ^= (currentSequence << 15) & 0xefc60000;
        currentSequence ^= (currentSequence >>> 18);

        return currentSequence;
    }
}
