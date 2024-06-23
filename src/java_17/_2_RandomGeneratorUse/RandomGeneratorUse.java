package java_17._2_RandomGeneratorUse;

import java.time.Clock;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

/**
 * @author atquil
 */
public class RandomGeneratorUse {
    public static void main(String[] args) {
        System.out.println("------Legacy---------");
        // 1. Using java.util.Random (legacy)
        Random legacyRandom = new Random();
        int legacyRandInt = legacyRandom.nextInt(100);
        System.out.println("Legacy Random Integer: " + legacyRandInt);

        // 2. Using ThreadLocalRandom (new)
        int newRandInt = ThreadLocalRandom.current().nextInt(100);
        System.out.println("New Random Integer: " + newRandInt);

        System.out.println("------RandomGenerator---------");
        // SplittableGenerator – can be split into two objects (the original one and a new one) each of which obeys that same protocol.
        SplittableRandom splittableRandom = new SplittableRandom();
        SplittableRandom splitRandom = splittableRandom.split();
        long splittableRandLong = splitRandom.nextLong();
        System.out.println("Splittable Random Long: " + splittableRandLong);



    }
}
