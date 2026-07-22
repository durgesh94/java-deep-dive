# Interfaces in Java

An **interface** is a fully abstract type that defines a contract (set of rules) that implementing classes must follow. It specifies **what** a class must do, but not **how** it does it.

## Table of Contents

- [1. What is an Interface?](#1-what-is-an-interface)
- [2. Syntax and Basic Example](#2-syntax-and-basic-example)
- [3. Implementing an Interface](#3-implementing-an-interface)
- [4. Multiple Interface Implementation](#4-multiple-interface-implementation)
- [5. Interface Fields (Constants)](#5-interface-fields-constants)
- [6. Default Methods (Java 8+)](#6-default-methods-java-8)
- [7. Static Methods (Java 8+)](#7-static-methods-java-8)
- [8. Private Methods (Java 9+)](#8-private-methods-java-9)
- [9. Interface Inheritance (extends)](#9-interface-inheritance-extends)
- [10. Functional Interfaces](#10-functional-interfaces)
- [11. Marker Interfaces](#11-marker-interfaces)
- [12. Interface vs Abstract Class](#12-interface-vs-abstract-class)
- [13. Diamond Problem](#13-diamond-problem)
- [14. Real-World Use Cases](#14-real-world-use-cases)
- [15. Best Practices](#15-best-practices)

---

## 1. What is an Interface?

An interface is like a **contract** — any class that implements it **promises** to provide implementations for all its abstract methods.

### Key Characteristics

- All methods are `public abstract` by default (before Java 8)
- All fields are `public static final` by default (constants)
- Cannot be instantiated directly
- A class can implement **multiple** interfaces (solves multiple inheritance problem)
- Supports **polymorphism** — an interface reference can point to any implementing object

---

## 2. Syntax and Basic Example

```java
// Defining an interface
public interface Animal {
    // Abstract method (no body) — implicitly public abstract
    void eat();
    void sleep();
    String makeSound();
}
```

### Rules for Interface Declaration

```java
public interface InterfaceName {
    // Constants (implicitly public static final)
    int MAX_VALUE = 100;

    // Abstract methods (implicitly public abstract)
    void doSomething();

    // Default methods (Java 8+)
    default void defaultMethod() { }

    // Static methods (Java 8+)
    static void staticMethod() { }

    // Private methods (Java 9+)
    private void helper() { }
}
```

---

## 3. Implementing an Interface

A class uses the `implements` keyword to implement an interface and **must provide body** for all abstract methods.

```java
public interface Drawable {
    void draw();
    double getArea();
}

public class Circle implements Drawable {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

public class Rectangle implements Drawable {
    double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle " + width + "x" + height);
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

// Polymorphism with interfaces
Drawable shape1 = new Circle(5);
Drawable shape2 = new Rectangle(4, 6);
shape1.draw();  // Drawing a circle with radius: 5
shape2.draw();  // Drawing a rectangle 4x6
```

### Key Points

- Implementing class **must** override all abstract methods (or be declared `abstract` itself)
- Methods in implementing class **must** be `public` (cannot reduce visibility)
- Use `@Override` annotation for clarity and compile-time checking

---

## 4. Multiple Interface Implementation

A class can implement **multiple interfaces** — this is Java's solution to multiple inheritance.

```java
public interface Flyable {
    void fly();
    default String getType() { return "Flying object"; }
}

public interface Swimmable {
    void swim();
    default String getType() { return "Swimming object"; }
}

public interface Runnable {
    void run();
}

// Implementing multiple interfaces
public class Duck implements Flyable, Swimmable, Runnable {
    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }

    @Override
    public void run() {
        System.out.println("Duck is running");
    }

    // Must resolve conflict when multiple interfaces have same default method
    @Override
    public String getType() {
        return "Duck - can fly, swim, and run";
    }
}

// Using interface references
Flyable flyer = new Duck();
Swimmable swimmer = new Duck();
flyer.fly();       // Duck is flying
swimmer.swim();    // Duck is swimming
```

### Class + Interface Together

```java
public class Animal {
    String name;
    void eat() { System.out.println(name + " is eating"); }
}

// Extends one class + implements multiple interfaces
public class Eagle extends Animal implements Flyable, Runnable {
    @Override
    public void fly() { System.out.println(name + " is soaring"); }

    @Override
    public void run() { System.out.println(name + " is running"); }
}
```

---

## 5. Interface Fields (Constants)

All fields in an interface are implicitly `public static final` — they are **constants**.

```java
public interface GameConstants {
    // These are all equivalent:
    int MAX_PLAYERS = 10;                    // implicitly public static final
    public static final int MIN_PLAYERS = 2; // explicitly declared

    String GAME_NAME = "Chess";
    double VERSION = 2.5;
}

// Accessing constants
System.out.println(GameConstants.MAX_PLAYERS); // 10
System.out.println(GameConstants.GAME_NAME);   // Chess
```

### Rules

- Must be initialized at declaration (no blank finals)
- Cannot be modified (they are `final`)
- Accessed using `InterfaceName.FIELD_NAME`
- Naming convention: `UPPER_SNAKE_CASE`

---

## 6. Default Methods (Java 8+)

Default methods allow adding **method implementations** in interfaces without breaking existing implementing classes.

```java
public interface Vehicle {
    void start();
    void stop();

    // Default method — has a body
    default void honk() {
        System.out.println("Beep beep!");
    }

    default void displayInfo() {
        System.out.println("This is a vehicle");
    }
}

public class Car implements Vehicle {
    @Override
    public void start() { System.out.println("Car started"); }

    @Override
    public void stop() { System.out.println("Car stopped"); }

    // Can optionally override default methods
    @Override
    public void honk() {
        System.out.println("Car horn: HONK!");
    }

    // displayInfo() is inherited as-is
}

Car car = new Car();
car.honk();         // Car horn: HONK! (overridden)
car.displayInfo();  // This is a vehicle (inherited default)
```

### Why Default Methods Were Added

- To allow **interface evolution** without breaking backward compatibility
- Example: `List.sort()`, `Collection.stream()` were added as default methods in Java 8
- Enables adding new functionality to interfaces in libraries

---

## 7. Static Methods (Java 8+)

Static methods belong to the **interface itself**, not to implementing classes.

```java
public interface MathOperations {
    double calculate(double a, double b);

    // Static utility method
    static double square(double n) {
        return n * n;
    }

    static double cube(double n) {
        return n * n * n;
    }

    static boolean isPositive(double n) {
        return n > 0;
    }
}

// Called on the interface, NOT on implementing class
double sq = MathOperations.square(5);    // 25.0
double cu = MathOperations.cube(3);      // 27.0
boolean pos = MathOperations.isPositive(-1); // false
```

### Key Points

- Called using `InterfaceName.methodName()` — NOT through object reference
- **Not inherited** by implementing classes
- Cannot be overridden
- Useful for **utility/helper methods** related to the interface

---

## 8. Private Methods (Java 9+)

Private methods allow code reuse **within** the interface — used as helpers for default/static methods.

```java
public interface Logger {
    default void logInfo(String message) {
        log("INFO", message);
    }

    default void logError(String message) {
        log("ERROR", message);
    }

    default void logWarning(String message) {
        log("WARNING", message);
    }

    // Private method — shared logic, not exposed to implementing classes
    private void log(String level, String message) {
        System.out.println("[" + level + "] " + java.time.LocalDateTime.now() + " - " + message);
    }

    // Private static method — helper for static methods
    private static String formatMessage(String msg) {
        return msg.trim().toUpperCase();
    }

    static void printFormatted(String message) {
        System.out.println(formatMessage(message));
    }
}
```

### Rules

- `private` methods — can be used by `default` methods
- `private static` methods — can be used by both `default` and `static` methods
- **Not accessible** from implementing classes
- Cannot be `abstract` (must have a body)

---

## 9. Interface Inheritance (extends)

An interface can **extend** one or more interfaces.

```java
public interface Readable {
    void read();
}

public interface Writable {
    void write();
}

// Interface extending multiple interfaces
public interface ReadWritable extends Readable, Writable {
    void readWrite();
}

// Implementing class must implement ALL methods from the hierarchy
public class File implements ReadWritable {
    @Override
    public void read() { System.out.println("Reading file"); }

    @Override
    public void write() { System.out.println("Writing file"); }

    @Override
    public void readWrite() { System.out.println("Reading and writing file"); }
}
```

### Interface Inheritance Hierarchy

```
        Readable          Writable
            \                /
             \              /
              ReadWritable
                   |
                  File (implements)
```

### Key Rules

- Interface uses `extends` (not `implements`) to inherit from other interfaces
- An interface can extend **multiple** interfaces (unlike classes)
- The implementing class must implement methods from **all** interfaces in the hierarchy

---

## 10. Functional Interfaces

A functional interface has **exactly one abstract method**. Used with **lambda expressions** and **method references**.

```java
// Annotated with @FunctionalInterface (optional but recommended)
@FunctionalInterface
public interface Calculator {
    double calculate(double a, double b);

    // Default and static methods don't count
    default void printResult(double result) {
        System.out.println("Result: " + result);
    }
}

// Using lambda expression
Calculator add = (a, b) -> a + b;
Calculator subtract = (a, b) -> a - b;
Calculator multiply = (a, b) -> a * b;
Calculator divide = (a, b) -> b != 0 ? a / b : 0;

System.out.println(add.calculate(10, 5));      // 15.0
System.out.println(subtract.calculate(10, 5)); // 5.0
System.out.println(multiply.calculate(10, 5)); // 50.0
```

### Built-in Functional Interfaces (java.util.function)

| Interface         | Method                | Description               | Example                     |
|-------------------|-----------------------|---------------------------|-----------------------------|
| `Predicate<T>`   | `boolean test(T t)`   | Test a condition          | `x -> x > 5`               |
| `Function<T,R>`  | `R apply(T t)`        | Transform T to R          | `s -> s.length()`           |
| `Consumer<T>`    | `void accept(T t)`   | Perform action on T       | `s -> System.out.println(s)`|
| `Supplier<T>`    | `T get()`            | Supply a value            | `() -> new ArrayList<>()`   |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | Two inputs, one output  | `(a, b) -> a + b`          |
| `UnaryOperator<T>` | `T apply(T t)`      | Same input/output type    | `x -> x * 2`               |

```java
import java.util.function.*;

Predicate<Integer> isEven = n -> n % 2 == 0;
Function<String, Integer> strLength = String::length;
Consumer<String> printer = System.out::println;
Supplier<Double> randomNum = Math::random;

System.out.println(isEven.test(4));       // true
System.out.println(strLength.apply("Hi")); // 2
printer.accept("Hello");                   // Hello
System.out.println(randomNum.get());       // 0.xyz...
```

---

## 11. Marker Interfaces

Interfaces with **no methods** — they "mark" a class as having a certain capability.

```java
// Built-in marker interfaces
public interface Serializable { }    // marks class as serializable
public interface Cloneable { }       // marks class as cloneable
public interface RandomAccess { }    // marks list as supporting fast random access

// Custom marker interface
public interface Deletable { }

public class TempFile implements Deletable {
    String name;
    // ...
}

// Using marker interface for type checking
public void cleanUp(Object obj) {
    if (obj instanceof Deletable) {
        System.out.println("Deleting: " + obj);
    }
}
```

> **Note:** In modern Java, **annotations** (`@Serializable`) are often preferred over marker interfaces.

---

## 12. Interface vs Abstract Class

| Feature                | Interface                              | Abstract Class                    |
|------------------------|----------------------------------------|-----------------------------------|
| Keyword                | `interface`                            | `abstract class`                  |
| Methods                | Abstract + default + static + private  | Abstract + concrete               |
| Fields                 | `public static final` only             | Any type (instance, static, etc.) |
| Constructors           | ❌ No                                  | ✅ Yes                            |
| Multiple inheritance   | ✅ Yes (implements multiple)           | ❌ No (extends one)               |
| Access modifiers       | Methods are `public` only              | Any access modifier               |
| Instance variables     | ❌ No                                  | ✅ Yes                            |
| When to use            | Define a **contract/capability**       | Share **common code** among related classes |
| Relationship           | "**can-do**" (capability)              | "**is-a**" (identity)             |

### When to Use Which?

```java
// Use INTERFACE when:
// - Unrelated classes need the same capability
// - You need multiple inheritance
// - Defining a contract/API

interface Printable { void print(); }   // Any class can be printable
interface Sortable { void sort(); }     // Any class can be sortable

// Use ABSTRACT CLASS when:
// - Related classes share common code
// - You need instance variables or constructors
// - You want to provide partial implementation

abstract class Shape {
    String color;  // shared state
    Shape(String color) { this.color = color; }  // constructor
    abstract double getArea();  // must implement
    void display() { System.out.println(color + " shape"); }  // shared code
}
```

---

## 13. Diamond Problem

When a class implements two interfaces with the **same default method**, a conflict occurs.

```java
interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void greet() {
        System.out.println("Hello from B");
    }
}

// COMPILE ERROR if greet() is not overridden
class C implements A, B {
    // MUST resolve the conflict by overriding
    @Override
    public void greet() {
        // Option 1: Provide own implementation
        System.out.println("Hello from C");

        // Option 2: Choose one interface's implementation
        A.super.greet();  // calls A's default method

        // Option 3: Call both
        A.super.greet();
        B.super.greet();
    }
}
```

### Resolution Rules

1. **Class wins over interface** — if a superclass provides a method, it takes priority over interface default methods
2. **More specific interface wins** — if interface B extends A, B's method wins
3. **Must override** — if no rule resolves the conflict, the class must explicitly override

---

## 14. Real-World Use Cases

### Payment Processing System

```java
public interface PaymentProcessor {
    boolean processPayment(double amount);
    boolean refund(String transactionId);
    String getPaymentMethod();

    default void printReceipt(double amount) {
        System.out.println("Payment of $" + amount + " via " + getPaymentMethod());
    }
}

public class CreditCardPayment implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
        return true;
    }

    @Override
    public boolean refund(String transactionId) {
        System.out.println("Refunding transaction: " + transactionId);
        return true;
    }

    @Override
    public String getPaymentMethod() { return "Credit Card"; }
}

public class UPIPayment implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment: $" + amount);
        return true;
    }

    @Override
    public boolean refund(String transactionId) {
        System.out.println("UPI refund: " + transactionId);
        return true;
    }

    @Override
    public String getPaymentMethod() { return "UPI"; }
}
```

### Comparable & Comparator

```java
// Comparable — natural ordering (implemented by the class itself)
public class Student implements Comparable<Student> {
    String name;
    double gpa;

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa);
    }
}

// Comparator — custom ordering (external)
Comparator<Student> byName = (s1, s2) -> s1.name.compareTo(s2.name);
Comparator<Student> byGpaDesc = (s1, s2) -> Double.compare(s2.gpa, s1.gpa);

Collections.sort(students, byName);
Collections.sort(students, byGpaDesc);
```

---

## 15. Best Practices

1. **Keep interfaces small and focused** (Interface Segregation Principle)
   ```java
   // Bad: too many responsibilities
   interface Worker { void code(); void test(); void manage(); void design(); }

   // Good: separated by responsibility
   interface Coder { void code(); }
   interface Tester { void test(); }
   interface Manager { void manage(); }
   ```

2. **Use `@FunctionalInterface`** for single-method interfaces

3. **Prefer interfaces over abstract classes** for defining types

4. **Program to the interface**, not the implementation
   ```java
   // Good
   List<String> names = new ArrayList<>();

   // Avoid
   ArrayList<String> names = new ArrayList<>();
   ```

5. **Use default methods sparingly** — don't overload interfaces with implementation logic

6. **Name interfaces based on capability** — `Runnable`, `Serializable`, `Comparable`, `Iterable`

7. **Don't use constant interfaces** (anti-pattern) — use `enum` or utility class for constants

8. **Document the contract** — clearly specify what implementors must guarantee
