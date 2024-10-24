package org.example.utils;

public class CastToRange {
    public static Integer cast(Integer number, Integer limit) {
        return Math.abs(number % limit);
    }
}
