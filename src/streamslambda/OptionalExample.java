package streamslambda;

import java.util.*;
import java.util.stream.*;

public class OptionalExample {

    record User(String name, String email) {}

    private static final List<User> DATABASE = List.of(
            new User("Durgesh", "durgesh@example.com"),
            new User("Alice", "alice@example.com")
    );

    // Repository-style method returning Optional instead of null
    public static Optional<User> findByName(String name) {
        return DATABASE.stream()
                .filter(u -> u.name().equalsIgnoreCase(name))
                .findFirst(); // findFirst() already returns Optional<T>
    }

    public static void main(String[] args) {

        // Case 1: value found
        Optional<User> found = findByName("Durgesh");
        found.ifPresentOrElse(
                u -> System.out.println("Found: " + u.email()),
                () -> System.out.println("User not found")
        );

        // Case 2: value not found — fallback chain
        String email = findByName("Bob")
                .map(User::email)
                .orElse("no-reply@example.com");
        System.out.println("Email: " + email);

        // Case 3: transform + filter + fallback, all chained
        String domain = findByName("Alice")
                .map(User::email)
                .filter(e -> e.contains("@"))
                .map(e -> e.substring(e.indexOf('@') + 1))
                .orElseThrow(() -> new NoSuchElementException("No valid email"));
        System.out.println("Domain: " + domain);

        // Case 4: throwing when critical data is missing
        try {
            findByName("Unknown")
                    .orElseThrow(() -> new IllegalArgumentException("No such user: Unknown"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
