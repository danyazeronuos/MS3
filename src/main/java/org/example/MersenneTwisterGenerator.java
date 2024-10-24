package org.example;

import org.example.model.Constants;
import org.example.utils.CastToRange;
import org.example.utils.IntegerGenerator;
import org.example.utils.SequenceGenerator;
import org.example.utils.TwistGenerator;

public class MersenneTwisterGenerator {
    private int[] mt;
    private int caret;

    public MersenneTwisterGenerator(int seed) {
        mt = SequenceGenerator.apply(seed);
    }

    public int nextInt() {
        if (caret >= Constants.SEQUENCE_SIZE.get()) {
            twist();
        }
        return IntegerGenerator.apply(mt[caret++]);
    }

    public int nextInt(int bound) {
        var generatedInteger = this.nextInt();
        return CastToRange.cast(generatedInteger, bound);
    }

    private void twist() {
        mt = TwistGenerator.apply(mt);
        caret = 0;
    }
}
