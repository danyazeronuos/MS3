package org.example.model;

public enum Constants {
//    SEQUENCE_SIZE(624),
    SEQUENCE_SIZE(3500),
    UPPER_MASK(0x80000000),
    LOWER_MASK(0x7fffffff),
    M(397);

    private final int value;

    public int get() {
        return value;
    }

    Constants(int value) {
        this.value = value;
    }

}
