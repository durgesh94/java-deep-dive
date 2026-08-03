package streamslambda;

import java.util.*;
import java.util.function.*;

public class MethodRefArbitraryObject {
    public static void main(String[] args) {

        // 1. String::toUpperCase — the object being operated on IS the argument
        Function<String, String> upper = String::toUpperCase;
        System.out.println(upper.apply("hello")); // HELLO
        // equivalent lambda: s -> s.toUpperCase()

        // 2. String::length
        Function<String, Integer> len = String::length;
        System.out.println(len.apply("Durgesh")); // 7
        // equivalent lambda: s -> s.length()

        // 3. String::compareTo — TWO params: first is the receiver, second is the arg
        BiFunction<String, String, Integer> compare = String::compareTo;
        System.out.println(compare.apply("apple", "banana")); // negative
        // equivalent lambda: (a, b) -> a.compareTo(b)

        // 4. String::isEmpty as a Predicate
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));      // true
        System.out.println(isEmpty.test("text"));  // false

        // 5. Using it in a real stream pipeline — very common usage
        List<String> names = List.of("charlie", "alice", "bob");
        names.stream()
                .map(String::toUpperCase)     // arbitrary-object method reference
                .sorted(String::compareTo)     // BiFunction-style comparator reference
                .forEach(System.out::println); // bound method reference (println on System.out)

        // 6. Custom class example — same pattern
        List<Employee> employees = List.of(
                new Employee("Alice", 50000),
                new Employee("Bob", 45000)
        );

        // Comparator.comparing takes a Function<Employee, ...> — arbitrary-object ref
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(e -> System.out.println(e.getName()));

        Function<Employee, Double> getSalary = Employee::getSalary;
        System.out.println(getSalary.apply(employees.get(0))); // 50000.0
    }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
}
