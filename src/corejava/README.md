# Core Java

A comprehensive guide covering fundamental Java concepts from basics to advanced topics.

## Table of Contents

- [01 - Variables & Data Types](#01---variables--data-types)
- [02 - Operators](#02---operators)
- [03 - Control Statements](#03---control-statements)
- [04 - Loops](#04---loops)
- [05 - Arrays](#05---arrays)
- [06 - Methods](#06---methods)
- [07 - Strings](#07---strings)
- [08 - Classes & Objects](#08---classes--objects)
- [09 - Constructors](#09---constructors)
- [10 - Inheritance](#10---inheritance)
- [11 - Polymorphism](#11---polymorphism)
- [12 - Abstraction](#12---abstraction)
- [13 - Encapsulation](#13---encapsulation)
- [14 - Interfaces](#14---interfaces)
- [15 - Exception Handling](#15---exception-handling)
- [16 - File Handling](#16---file-handling)

---

## 01 - Variables & Data Types

Variables are containers for storing data values. Java is a statically-typed language, meaning every variable must be declared with a data type.

### Primitive Data Types

| Data Type | Size    | Default Value | Range                                                    |
|-----------|---------|---------------|----------------------------------------------------------|
| `byte`    | 1 byte  | 0             | -128 to 127                                              |
| `short`   | 2 bytes | 0             | -32,768 to 32,767                                        |
| `int`     | 4 bytes | 0             | -2,147,483,648 to 2,147,483,647                          |
| `long`    | 8 bytes | 0L            | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807  |
| `float`   | 4 bytes | 0.0f          | ~6-7 decimal digits precision                            |
| `double`  | 8 bytes | 0.0d          | ~15 decimal digits precision                             |
| `char`    | 2 bytes | '\u0000'      | 0 to 65,535 (Unicode characters)                         |
| `boolean` | 1 bit   | false         | true or false                                            |

### Non-Primitive (Reference) Data Types

- **String** – sequence of characters
- **Arrays** – collection of similar data types
- **Classes** – user-defined blueprints
- **Interfaces** – abstract type definitions

### Type Casting

- **Widening (Implicit):** `byte → short → int → long → float → double` (automatic, no data loss)
- **Narrowing (Explicit):** `double → float → long → int → short → byte` (manual, potential data loss)

```java
int num = 100;
double d = num;        // Widening: int to double (automatic)
int x = (int) 9.78;   // Narrowing: double to int (manual) → x = 9
```

---

## 02 - Operators

Operators are special symbols that perform operations on variables and values.

### Types of Operators

| Category       | Operators                              | Description                          |
|----------------|----------------------------------------|--------------------------------------|
| Arithmetic     | `+`, `-`, `*`, `/`, `%`               | Basic math operations                |
| Assignment     | `=`, `+=`, `-=`, `*=`, `/=`, `%=`     | Assign values to variables           |
| Comparison     | `==`, `!=`, `>`, `<`, `>=`, `<=`      | Compare two values, returns boolean  |
| Logical        | `&&`, `||`, `!`                        | Combine conditional statements       |
| Bitwise        | `&`, `|`, `^`, `~`, `<<`, `>>`, `>>>` | Operate on bits                      |
| Unary          | `++`, `--`, `+`, `-`, `!`             | Operate on a single operand          |
| Ternary        | `? :`                                 | Shorthand for if-else                |
| `instanceof`   | `instanceof`                          | Check object type                    |

### Operator Precedence (High to Low)

1. Postfix: `expr++`, `expr--`
2. Unary: `++expr`, `--expr`, `+`, `-`, `!`
3. Multiplicative: `*`, `/`, `%`
4. Additive: `+`, `-`
5. Relational: `<`, `>`, `<=`, `>=`, `instanceof`
6. Equality: `==`, `!=`
7. Logical AND: `&&`
8. Logical OR: `||`
9. Ternary: `? :`
10. Assignment: `=`, `+=`, `-=`

---

## 03 - Control Statements

Control statements determine the flow of execution in a program.

### Decision-Making Statements

```java
// if-else
if (condition) {
    // executes if condition is true
} else if (anotherCondition) {
    // executes if anotherCondition is true
} else {
    // executes if all conditions are false
}

// switch
switch (expression) {
    case value1:
        // code block
        break;
    case value2:
        // code block
        break;
    default:
        // default code block
}
```

### Key Points

- `switch` works with `byte`, `short`, `int`, `char`, `String`, and enums
- `break` exits the switch block; without it, execution "falls through" to the next case
- `default` is optional and executes when no case matches
- Java 14+ supports **switch expressions** with `->` arrow syntax

---

## 04 - Loops

Loops execute a block of code repeatedly based on a condition.

### Types of Loops

```java
// for loop – when iteration count is known
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// while loop – when iteration count is unknown
while (condition) {
    // code block
}

// do-while loop – executes at least once
do {
    // code block
} while (condition);

// enhanced for-each loop – for arrays/collections
for (int element : array) {
    System.out.println(element);
}
```

### Loop Control Statements

- `break` – exits the loop immediately
- `continue` – skips the current iteration and moves to the next
- **Labeled break/continue** – used with nested loops to break/continue a specific outer loop

---

## 05 - Arrays

An array is a fixed-size, ordered collection of elements of the same data type.

### Declaration & Initialization

```java
// Declaration and allocation
int[] numbers = new int[5];

// Declaration with initialization
int[] numbers = {10, 20, 30, 40, 50};

// Multi-dimensional array
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};
```

### Key Points

- Arrays are **zero-indexed**
- Array size is fixed once created
- `length` property gives the size of the array
- `ArrayIndexOutOfBoundsException` is thrown for invalid index access
- Arrays are objects in Java and stored on the **heap**
- Use `Arrays.sort()`, `Arrays.copyOf()`, `Arrays.toString()` from `java.util.Arrays`

---

## 06 - Methods

Methods are blocks of code that perform a specific task and are executed when called.

### Syntax

```java
accessModifier returnType methodName(parameters) {
    // method body
    return value; // if returnType is not void
}
```

### Types of Methods

- **Instance methods** – belong to an object, require object to call
- **Static methods** – belong to the class, called using class name
- **Abstract methods** – declared without a body, must be implemented by subclass

### Key Concepts

- **Method Overloading** – same method name, different parameter lists (compile-time polymorphism)
- **Varargs** – variable number of arguments: `void method(int... nums)`
- **Pass by Value** – Java always passes arguments by value (for objects, the reference is passed by value)
- **Recursion** – a method calling itself

---

## 07 - Strings

Strings are immutable sequences of characters. Java provides the `String` class in `java.lang`.

### Creating Strings

```java
String s1 = "Hello";            // String literal (stored in String Pool)
String s2 = new String("Hello"); // String object (stored in Heap)
```

### Important String Methods

| Method                    | Description                                |
|---------------------------|--------------------------------------------|
| `length()`                | Returns the length of the string           |
| `charAt(int index)`       | Returns character at specified index       |
| `substring(int, int)`     | Extracts a portion of the string           |
| `equals(String)`          | Compares content (case-sensitive)          |
| `equalsIgnoreCase(String)`| Compares content (case-insensitive)        |
| `contains(CharSequence)`  | Checks if string contains a sequence       |
| `indexOf(String)`         | Returns index of first occurrence          |
| `toUpperCase()` / `toLowerCase()` | Converts case                     |
| `trim()` / `strip()`      | Removes leading/trailing whitespace        |
| `replace(old, new)`       | Replaces characters/substrings             |
| `split(String regex)`     | Splits string into an array                |

### String vs StringBuilder vs StringBuffer

| Feature       | String        | StringBuilder  | StringBuffer   |
|---------------|---------------|----------------|----------------|
| Mutability    | Immutable     | Mutable        | Mutable        |
| Thread Safety | Yes (immutable)| No            | Yes (synchronized) |
| Performance   | Slow (creates new objects) | Fast | Slower than StringBuilder |

---

## 08 - Classes & Objects

A **class** is a blueprint for creating objects. An **object** is an instance of a class.

### Class Structure

```java
public class Car {
    // Fields (attributes)
    String brand;
    int speed;

    // Method (behavior)
    void accelerate() {
        speed += 10;
    }
}

// Creating an object
Car myCar = new Car();
myCar.brand = "Toyota";
myCar.accelerate();
```

### Key Concepts

- **`this` keyword** – refers to the current object instance
- **`static` keyword** – belongs to the class rather than instances
- **`final` keyword** – makes variables constant, methods non-overridable, classes non-inheritable
- **Access Modifiers:** `public`, `private`, `protected`, default (package-private)
- **Getter/Setter** – methods to access and modify private fields

---

## 09 - Constructors

A constructor is a special method that initializes an object when it is created.

### Types of Constructors

```java
public class Student {
    String name;
    int age;

    // Default constructor (no-arg)
    public Student() {
        name = "Unknown";
        age = 0;
    }

    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Copy constructor
    public Student(Student other) {
        this.name = other.name;
        this.age = other.age;
    }
}
```

### Key Points

- Constructor name must match the class name
- No return type (not even `void`)
- Called automatically when `new` is used
- **Constructor Overloading** – multiple constructors with different parameters
- **Constructor Chaining** – using `this()` to call another constructor in the same class
- If no constructor is defined, Java provides a **default no-arg constructor**

---

## 10 - Inheritance

Inheritance allows a class (child/subclass) to inherit fields and methods from another class (parent/superclass).

### Syntax

```java
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}
```

### Types of Inheritance

| Type            | Description                                    | Supported in Java? |
|-----------------|------------------------------------------------|--------------------|
| Single          | One child extends one parent                   | Yes                |
| Multilevel      | A → B → C (chain)                             | Yes                |
| Hierarchical    | One parent, multiple children                  | Yes                |
| Multiple        | One child extends multiple parents             | No (use interfaces)|
| Hybrid          | Combination of multiple types                  | No (use interfaces)|

### Key Points

- `super` keyword – refers to the parent class (call parent constructor/methods)
- `super()` must be the first statement in a child constructor
- **Method Overriding** – child provides its own implementation of a parent method
- `@Override` annotation is recommended for overridden methods

---

## 11 - Polymorphism

Polymorphism means "many forms" – the ability of an object to take different forms.

### Compile-Time Polymorphism (Method Overloading)

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
```

### Runtime Polymorphism (Method Overriding)

```java
class Shape {
    void draw() { System.out.println("Drawing shape"); }
}

class Circle extends Shape {
    @Override
    void draw() { System.out.println("Drawing circle"); }
}

Shape s = new Circle(); // Upcasting
s.draw(); // Output: "Drawing circle" (resolved at runtime)
```

### Key Points

- **Upcasting** – child reference stored in parent type (automatic)
- **Downcasting** – parent reference cast to child type (explicit, use `instanceof` to check)
- Overloading is resolved at **compile time**, overriding at **runtime**
- `static`, `final`, and `private` methods cannot be overridden

---

## 12 - Abstraction

Abstraction hides implementation details and shows only the essential features.

### Abstract Classes

```java
abstract class Vehicle {
    abstract void start(); // abstract method (no body)

    void stop() { // concrete method
        System.out.println("Vehicle stopped");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car started with key");
    }
}
```

### Key Points

- Declared with `abstract` keyword
- Cannot be instantiated directly
- Can have both abstract and concrete methods
- Can have constructors, fields, and static methods
- A class extending an abstract class **must implement all abstract methods** (or be abstract itself)
- Provides **0 to 100% abstraction**

---

## 13 - Encapsulation

Encapsulation is the practice of wrapping data (fields) and methods into a single unit (class) and restricting direct access.

### Implementation

```java
public class BankAccount {
    private double balance; // private field

    // Getter
    public double getBalance() {
        return balance;
    }

    // Setter with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
```

### Access Modifiers

| Modifier    | Class | Package | Subclass | World |
|-------------|-------|---------|----------|-------|
| `public`    | Yes   | Yes     | Yes      | Yes   |
| `protected` | Yes   | Yes     | Yes      | No    |
| Default     | Yes   | Yes     | No       | No    |
| `private`   | Yes   | No      | No       | No    |

### Benefits

- **Data Hiding** – internal state is protected from unauthorized access
- **Controlled Access** – getters/setters allow validation before modification
- **Flexibility** – internal implementation can change without affecting external code
- **Maintainability** – easier to debug and modify

---

## 14 - Interfaces

An interface is a fully abstract type that defines a contract for classes to implement.

### Syntax

```java
interface Drawable {
    void draw(); // abstract by default (public abstract)

    default void display() { // default method (Java 8+)
        System.out.println("Displaying...");
    }

    static void info() { // static method (Java 8+)
        System.out.println("Drawable interface");
    }
}

class Rectangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
    }
}
```

### Key Points

- All fields are `public static final` (constants) by default
- All methods are `public abstract` by default
- A class can implement **multiple interfaces** (solves multiple inheritance)
- Interfaces can extend other interfaces
- **Java 8+:** `default` and `static` methods allowed
- **Java 9+:** `private` methods allowed in interfaces
- **Functional Interface** – an interface with exactly one abstract method (used with lambdas)

### Abstract Class vs Interface

| Feature          | Abstract Class          | Interface                  |
|------------------|-------------------------|----------------------------|
| Methods          | Abstract + concrete     | Abstract + default + static|
| Fields           | Any type                | Only public static final   |
| Constructors     | Yes                     | No                         |
| Inheritance       | Single (extends)       | Multiple (implements)      |
| Access Modifiers | Any                     | Public only (methods)      |

---

## 15 - Exception Handling

Exception handling manages runtime errors to maintain normal program flow.

### Exception Hierarchy

```
Throwable
├── Error (unrecoverable: OutOfMemoryError, StackOverflowError)
└── Exception
    ├── Checked Exceptions (compile-time: IOException, SQLException)
    └── RuntimeException (unchecked: NullPointerException, ArithmeticException)
```

### Syntax

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} catch (Exception e) {
    System.out.println("General exception: " + e.getMessage());
} finally {
    System.out.println("Always executes");
}
```

### Key Concepts

- **try-catch** – catch and handle exceptions
- **finally** – always executes (cleanup code)
- **throw** – manually throw an exception: `throw new IllegalArgumentException("Invalid");`
- **throws** – declare exceptions a method might throw: `void read() throws IOException`
- **try-with-resources** (Java 7+) – auto-closes resources implementing `AutoCloseable`
- **Custom Exceptions** – extend `Exception` (checked) or `RuntimeException` (unchecked)

```java
// Custom exception
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
```

---

## 16 - File Handling

Java provides classes in `java.io` and `java.nio` packages for file operations.

### Common Classes

| Class              | Purpose                                        |
|--------------------|------------------------------------------------|
| `File`             | Represents a file/directory path               |
| `FileReader`       | Reads characters from a file                   |
| `FileWriter`       | Writes characters to a file                    |
| `BufferedReader`   | Reads text efficiently with buffering          |
| `BufferedWriter`   | Writes text efficiently with buffering         |
| `FileInputStream`  | Reads raw bytes from a file                    |
| `FileOutputStream` | Writes raw bytes to a file                     |
| `Scanner`          | Reads input from files (and other sources)     |
| `Files` (NIO)      | Modern utility class for file operations       |
| `Path` (NIO)       | Represents file/directory paths                |

### Examples

```java
// Writing to a file
try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
    writer.write("Hello, World!");
}

// Reading from a file
try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}

// Using NIO (Java 7+)
Path path = Path.of("output.txt");
Files.writeString(path, "Hello NIO!");
String content = Files.readString(path);
```

### Key Points

- Always close file resources (use try-with-resources)
- Use `BufferedReader`/`BufferedWriter` for better performance
- `java.nio.file.Files` provides modern, concise file operations
- Handle `IOException` when working with files
- Use `File.separator` or `Path` for cross-platform file paths
