package org.example;

public class Main {

    public static void main(String[] args) {
        MersenneTwisterGenerator mt = new MersenneTwisterGenerator(12345);

        for (int i = 0; i < 100; i++) {
            System.out.println(mt.nextInt());
        }
    }
}
