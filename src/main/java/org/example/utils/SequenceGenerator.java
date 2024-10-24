package org.example.utils;

import org.example.model.Constants;

import java.util.function.Function;

public class SequenceGenerator {
    public static int[] apply(Integer seed) {
        var mt = new int[Constants.SEQUENCE_SIZE.get()];
        mt[0] = seed;

        for (var mti = 1; mti < Constants.SEQUENCE_SIZE.get(); mti++) {
            mt[mti] = (1812433253 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30)) + mti);
        }

        return mt;
    }
}
