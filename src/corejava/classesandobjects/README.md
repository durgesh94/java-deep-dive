# Classes and Objects in Java

A **class** is a blueprint/template for creating objects. An **object** is an instance of a class that holds actual values.

## Table of Contents

- [1. Class](#1-class)
- [2. Object](#2-object)
- [3. Fields (Instance Variables)](#3-fields-instance-variables)
- [4. Methods](#4-methods)
- [5. Access Modifiers](#5-access-modifiers)
- [6. Constructors](#6-constructors)
- [7. `this` Keyword](#7-this-keyword)
- [8. `static` Keyword](#8-static-keyword)
- [9. `final` Keyword](#9-final-keyword)
- [10. Getters and Setters](#10-getters-and-setters)
- [11. `toString()` Method](#11-tostring-method)
- [12. Object Class](#12-object-class)
- [13. Anonymous Objects](#13-anonymous-objects)
- [14. Inner Classes](#14-inner-classes)
- [15. Class vs Object Summary](#15-class-vs-object-summary)

---

## 1. Class

A class defines the structure and behavior that objects of that type will have.

### Syntax

```java
accessModifier class ClassName {
    // fields (attributes/properties)
    // constructors
    // methods (behavior)
}
```

### Example

```java
public class Car {
    // Fields
    String brand;
    String color;
    int speed;

    // Method
    void accelerate() {
        speed += 10;
        System.out.println(brand + " is now going " + speed + " km/h");
    }

    void brake() {
        speed = Math.max(0, speed - 10);
        System.out.println(brand + " slowed to " + speed + " km/h");
    }
}
```

### Key Points

- Class name should start with an **uppercase letter** (PascalCase)
- A `.java` file can have only **one public class**, and its name must match the file name
- A class can contain: fields, methods, constructors, blocks, nested classes

---

## 2. Object

An object is a runtime instance of a class. It occupies memory on the **heap**.

### Creating Objects

```java
// Syntax: ClassName objectName = new ClassName();
Car myCar = new Car();
myCar.brand = "Toyota";
myCar.color = "Red";
myCar.accelerate(); // Toyota is now going 10 km/h

Car anotherCar = new Car();
anotherCar.brand = "Honda";
anotherCar.accelerate(); // Honda is now going 10 km/h
```

### What Happens During Object Creation

1. **`new`** allocates memory on the heap
2. **Constructor** is called to initialize the object
3. A **reference** to the object is returned and stored in the variable

```
Stack                    Heap
┌──────────┐            ┌──────────────────┐
│ myCar ───┼──────────► │ Car Object       │
│          │            │ brand = "Toyota"  │
└──────────┘            │ color = "Red"     │
                        │ speed = 10        │
                        └──────────────────┘
```

### Key Points

- Multiple objects can be created from the same class
- Each object has its **own copy** of instance variables
- Objects are passed by **reference value** (the reference is copied, not the object)

---

## 3. Fields (Instance Variables)

Fields represent the **state/attributes** of an object.

```java
public class Student {
    // Instance variables (each object gets its own copy)
    String name;
    int age;
    double gpa;

    // Static variable (shared across all objects)
    static int totalStudents = 0;
}
```

### Default Values

| Data Type          | Default Value |
|--------------------|---------------|
| `int`, `short`, `byte`, `long` | `0`   |
| `float`, `double`  | `0.0`        |
| `char`             | `'\u0000'`   |
| `boolean`          | `false`      |
| Object references  | `null`       |

### Variable Types

| Type            | Declared In        | Scope              | Stored In |
|-----------------|--------------------|---------------------|-----------|
| Instance        | Class (no static)  | Object lifetime     | Heap      |
| Static (Class)  | Class (with static)| Class lifetime      | Method Area |
| Local           | Method/block       | Method/block execution | Stack |

---

## 4. Methods

Methods define the **behavior** of an object.

### Syntax

```java
accessModifier returnType methodName(parameters) {
    // method body
    return value; // if returnType is not void
}
```

### Types of Methods

```java
public class Calculator {
    // Instance method — called on an object
    public int add(int a, int b) {
        return a + b;
    }

    // Static method — called on the class
    public static int multiply(int a, int b) {
        return a * b;
    }

    // Method with no return value
    public void printResult(int result) {
        System.out.println("Result: " + result);
    }

    // Method overloading — same name, different parameters
    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

// Usage
Calculator calc = new Calculator();
calc.add(5, 3);           // calls add(int, int) → 8
calc.add(2.5, 3.5);       // calls add(double, double) → 6.0
calc.add(1, 2, 3);        // calls add(int, int, int) → 6
Calculator.multiply(4, 5); // static call → 20
```

### Varargs (Variable Arguments)

```java
public int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) {
        total += n;
    }
    return total;
}

sum(1, 2);          // 3
sum(1, 2, 3, 4, 5); // 15
```

### Pass by Value

Java is always **pass by value**. For objects, the reference is passed by value.

```java
public void changeName(Student s) {
    s.name = "Alice";  // modifies the original object (same reference)
}

public void reassign(Student s) {
    s = new Student();  // does NOT affect the original reference
    s.name = "Bob";
}
```

---

## 5. Access Modifiers

Control the visibility of classes, fields, and methods.

| Modifier    | Same Class | Same Package | Subclass (other pkg) | Everywhere |
|-------------|------------|--------------|----------------------|------------|
| `public`    | ✅         | ✅           | ✅                   | ✅         |
| `protected` | ✅         | ✅           | ✅                   | ❌         |
| Default     | ✅         | ✅           | ❌                   | ❌         |
| `private`   | ✅         | ❌           | ❌                   | ❌         |

```java
public class Employee {
    public String name;        // accessible everywhere
    protected int age;         // accessible in same package + subclasses
    String department;         // default: accessible in same package
    private double salary;     // accessible only within this class
}
```

---

## 6. Constructors

Special methods that initialize objects when created with `new`.

```java
public class Person {
    String name;
    int age;

    // Default constructor (no-arg)
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    // Parameterized constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Copy constructor
    public Person(Person other) {
        this.name = other.name;
        this.age = other.age;
    }
}

// Usage
Person p1 = new Person();                // default constructor
Person p2 = new Person("Alice", 25);     // parameterized constructor
Person p3 = new Person(p2);              // copy constructor
```

### Constructor Chaining

```java
public class Product {
    String name;
    double price;
    String category;

    public Product() {
        this("Unknown", 0.0, "General"); // calls 3-arg constructor
    }

    public Product(String name, double price) {
        this(name, price, "General"); // calls 3-arg constructor
    }

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
```

### Key Points

- Constructor name must **match the class name**
- **No return type** (not even `void`)
- If no constructor is defined, Java provides a **default no-arg constructor**
- `this()` must be the **first statement** in a constructor

---

## 7. `this` Keyword

Refers to the **current object** instance.

### Uses

```java
public class Book {
    String title;
    String author;

    // 1. Distinguish fields from parameters
    public Book(String title, String author) {
        this.title = title;   // this.title = field, title = parameter
        this.author = author;
    }

    // 2. Call another constructor
    public Book(String title) {
        this(title, "Unknown"); // calls the 2-arg constructor
    }

    // 3. Pass current object as argument
    public void register(Library library) {
        library.addBook(this);
    }

    // 4. Return current object (method chaining)
    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }
}

// Method chaining
Book book = new Book("Java")
    .setTitle("Effective Java")
    .setAuthor("Joshua Bloch");
```

---

## 8. `static` Keyword

Belongs to the **class** rather than any specific object.

### Static Fields

```java
public class Counter {
    static int count = 0; // shared across all objects

    public Counter() {
        count++; // increments for every new object
    }
}

Counter c1 = new Counter(); // count = 1
Counter c2 = new Counter(); // count = 2
System.out.println(Counter.count); // 2
```

### Static Methods

```java
public class MathUtils {
    public static int square(int n) {
        return n * n;
    }
}

// Called using class name, no object needed
MathUtils.square(5); // 25
```

### Static Block

```java
public class Config {
    static String dbUrl;

    // Static block — runs once when class is loaded
    static {
        dbUrl = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Config loaded");
    }
}
```

### Key Rules

| Can Access          | Static Method | Instance Method |
|---------------------|---------------|-----------------|
| Static fields       | ✅            | ✅              |
| Static methods      | ✅            | ✅              |
| Instance fields     | ❌            | ✅              |
| Instance methods    | ❌            | ✅              |
| `this` keyword      | ❌            | ✅              |

---

## 9. `final` Keyword

Makes things **unchangeable**.

```java
// Final variable — constant, cannot be reassigned
final int MAX_SIZE = 100;

// Final method — cannot be overridden
public final void display() {
    System.out.println("Cannot override this");
}

// Final class — cannot be extended (inherited)
public final class Constants {
    public static final double PI = 3.14159;
}

// Final parameter — cannot modify inside the method
public void greet(final String name) {
    // name = "other"; // compile error
    System.out.println("Hello, " + name);
}
```

### Blank Final Variable

```java
public class Order {
    final int orderId; // blank final — must be initialized in constructor

    public Order(int id) {
        this.orderId = id; // initialized here
    }
}
```

---

## 10. Getters and Setters

Provide controlled access to private fields (encapsulation).

```java
public class BankAccount {
    private String owner;
    private double balance;

    // Getter
    public String getOwner() {
        return owner;
    }

    // Setter with validation
    public void setOwner(String owner) {
        if (owner != null && !owner.isEmpty()) {
            this.owner = owner;
        }
    }

    public double getBalance() {
        return balance;
    }

    // No public setter for balance — controlled through methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}

// Usage
BankAccount account = new BankAccount();
account.setOwner("Alice");
account.deposit(1000);
account.withdraw(200);
System.out.println(account.getBalance()); // 800.0
```

---

## 11. `toString()` Method

Returns a string representation of the object. Called automatically in `System.out.println()`.

```java
public class Student {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

Student s = new Student("Alice", 20);
System.out.println(s); // Student{name='Alice', age=20}
// Without toString(): Student@1b6d3586 (hash code)
```

---

## 12. Object Class

Every class in Java implicitly extends `java.lang.Object`. Key methods:

| Method            | Description                                         |
|-------------------|-----------------------------------------------------|
| `toString()`      | String representation of the object                 |
| `equals(Object)`  | Checks logical equality (default: reference equality)|
| `hashCode()`      | Returns hash code for the object                    |
| `getClass()`      | Returns the runtime class of the object             |
| `clone()`         | Creates a copy of the object                        |
| `finalize()`      | Called by GC before object is destroyed (deprecated) |

### Overriding `equals()` and `hashCode()`

```java
public class Employee {
    int id;
    String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

> **Rule:** If you override `equals()`, you **must** also override `hashCode()`.

---

## 13. Anonymous Objects

Objects created without storing a reference. Used for one-time operations.

```java
// Anonymous object — no reference variable
new Car().accelerate();

// Passing anonymous object as argument
printDetails(new Student("Alice", 20));

// In method chaining
new StringBuilder("Hello").append(" World").append("!").toString();
```

---

## 14. Inner Classes

Classes defined inside another class.

### Types of Inner Classes

```java
public class Outer {
    int x = 10;

    // 1. Regular inner class (non-static)
    class Inner {
        void display() {
            System.out.println("x = " + x); // can access outer fields
        }
    }

    // 2. Static nested class
    static class StaticNested {
        void display() {
            System.out.println("Static nested class");
            // cannot access non-static outer members
        }
    }

    void demo() {
        // 3. Local inner class (inside a method)
        class LocalInner {
            void display() {
                System.out.println("Local inner class");
            }
        }
        new LocalInner().display();

        // 4. Anonymous inner class
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };
        r.run();
    }
}

// Usage
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();     // non-static inner
inner.display();

Outer.StaticNested nested = new Outer.StaticNested(); // static nested
nested.display();
```

### Comparison

| Type           | Access to Outer Members | Requires Outer Instance | Declared In  |
|----------------|------------------------|-------------------------|--------------|
| Inner class    | Yes (all)              | Yes                     | Class body   |
| Static nested  | Static only            | No                      | Class body   |
| Local inner    | Yes + final locals     | N/A                     | Method       |
| Anonymous      | Yes + final locals     | N/A                     | Expression   |

---

## 15. Class vs Object Summary

| Feature         | Class                               | Object                             |
|-----------------|--------------------------------------|--------------------------------------|
| Definition      | Blueprint/template                  | Instance of a class                 |
| Memory          | No memory allocated                 | Memory allocated on heap            |
| Creation        | Defined using `class` keyword       | Created using `new` keyword         |
| Count           | Defined once                        | Multiple objects from one class     |
| Contains        | Fields, methods, constructors       | Actual values of fields             |
| Example         | `class Car { ... }`                 | `Car myCar = new Car();`           |

### Real-World Analogy

```
Class (Blueprint)              Object (Instance)
┌─────────────────┐           ┌─────────────────┐
│ Car              │           │ myCar            │
│ - brand          │  ──new──► │ - brand = "BMW"  │
│ - color          │           │ - color = "Black"│
│ - speed          │           │ - speed = 0      │
│ + accelerate()   │           │                  │
│ + brake()        │           │                  │
└─────────────────┘           └─────────────────┘
                              ┌─────────────────┐
                    ──new──►  │ yourCar           │
                              │ - brand = "Audi" │
                              │ - color = "White"│
                              │ - speed = 0      │
                              └─────────────────┘
```
