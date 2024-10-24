package org.example.utils;

import org.example.model.Constants;

public class TwistGenerator {
    public static int[] apply(int[] previousSequence) {

        int[] mag01 = {0x0, 0x9908b0df};
        var newSequence = new int[previousSequence.length];

        for (int i = 0; i < Constants.SEQUENCE_SIZE.get() - 1; i++) {
            int y = (previousSequence[i] & Constants.UPPER_MASK.get()) | (previousSequence[i + 1] & Constants.LOWER_MASK.get());
            newSequence[i] = previousSequence[(i + Constants.M.get()) % Constants.SEQUENCE_SIZE.get()] ^ (y >>> 1) ^ mag01[y & 0x1];
        }

        return newSequence;
    }
}
