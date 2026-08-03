package streamslambda;

import java.util.*;
import java.util.function.*;

public class LambdaExample {
    public static void main(String[] args) {

        // 1. No-argument lambda
        Runnable greet = () -> System.out.println("Hello!");
        greet.run();

        // 2. Single-argument lambda
        Function<Integer, Integer> square = n -> n * n;
        System.out.println("Square of 5: " + square.apply(5));

        // 3. Multi-argument lambda
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("3 + 4 = " + add.apply(3, 4));

        // 4. Block body with multiple statements
        Function<Integer, String> classify = n -> {
            if (n % 2 == 0) {
                return n + " is even";
            } else {
                return n + " is odd";
            }
        };
        System.out.println(classify.apply(7));

        // 5. Lambda used directly with a collection
        List<String> names = new ArrayList<>(List.of("Charlie", "Alice", "Bob"));
        names.sort((a, b) -> a.compareTo(b));   // Comparator as lambda
        System.out.println(names);

        // 6. Lambda replacing an old-style anonymous class listener/callback
        Predicate<String> isLong = s -> s.length() > 4;
        names.forEach(n -> {
            if (isLong.test(n)) {
                System.out.println(n + " is long");
            }
        });
    }
}
