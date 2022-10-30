package com.lb.utils;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();
    private static final List<String> str = List.of("A", "a", "B", "b", "c", "C", "D", "d", "E", "e", "F", "f");

    public static Integer random() {
        return random(4);
    }

    public static Integer random(int bound) {
        return random.nextInt(bound);
    }

    public static String randomString() {
        return str.get(random.nextInt(str.size() - 1)) + str.get(random.nextInt(str.size() - 1)) +
                random(3) + str.get(random.nextInt(str.size() - 1)) + str.get(random.nextInt(str.size() - 1));
    }
}
