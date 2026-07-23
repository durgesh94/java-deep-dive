# Constructors in Java

A **constructor** is a special method that is automatically called when an object is created using the `new` keyword. Its primary purpose is to **initialize** the object's state.

## Table of Contents

- [1. What is a Constructor?](#1-what-is-a-constructor)
- [2. Default Constructor](#2-default-constructor)
- [3. No-Arg Constructor](#3-no-arg-constructor)
- [4. Parameterized Constructor](#4-parameterized-constructor)
- [5. Copy Constructor](#5-copy-constructor)
- [6. Constructor Overloading](#6-constructor-overloading)
- [7. Constructor Chaining (`this()`)](#7-constructor-chaining-this)
- [8. Constructor Chaining with Inheritance (`super()`)](#8-constructor-chaining-with-inheritance-super)
- [9. Private Constructor](#9-private-constructor)
- [10. Constructor vs Method](#10-constructor-vs-method)
- [11. Common Mistakes](#11-common-mistakes)
- [12. Best Practices](#12-best-practices)

---

## 1. What is a Constructor?

### Rules for Constructors

1. Constructor name **must match** the class name exactly
2. **No return type** — not even `void`
3. Called **automatically** when `new` is used
4. Cannot be `abstract`, `static`, `final`, or `synchronized`
5. Can have access modifiers: `public`, `private`, `protected`, or default

### Syntax

```java
public class ClassName {
    // Constructor
    accessModifier ClassName(parameters) {
        // initialization code
    }
}
```

---

## 2. Default Constructor

If you do **not** define any constructor, Java automatically provides a **default constructor** (no-arg, empty body).

```java
public class Animal {
    String name;
    int age;
    // Java provides: public Animal() { }
}

Animal a = new Animal();
System.out.println(a.name); // null (default for String)
System.out.println(a.age);  // 0 (default for int)
```

### Key Points

- Default constructor is provided **only** if no constructor is explicitly defined
- It initializes fields with their **default values** (0, null, false, etc.)
- Once you define any constructor, the default constructor is **no longer provided**

---

## 3. No-Arg Constructor

An explicitly defined constructor that takes no parameters.

```java
public class Student {
    String name;
    int age;

    // No-arg constructor (explicitly defined)
    public Student() {
        this.name = "Unknown";
        this.age = 0;
        System.out.println("Student object created");
    }
}

Student s = new Student(); // Output: Student object created
System.out.println(s.name); // Unknown
```

### When to Use

- To set **default values** that differ from Java's defaults
- To perform **initialization logic** (logging, validation, etc.)
- To ensure a no-arg constructor exists alongside parameterized constructors

---

## 4. Parameterized Constructor

A constructor that accepts parameters to initialize fields with custom values.

```java
public class Employee {
    String name;
    int id;
    double salary;

    // Parameterized constructor
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
}

Employee emp = new Employee("Alice", 101, 75000.0);
System.out.println(emp.name);   // Alice
System.out.println(emp.id);     // 101
System.out.println(emp.salary); // 75000.0
```

### Multiple Parameterized Constructors

```java
public class Rectangle {
    double width;
    double height;

    // Square (equal sides)
    public Rectangle(double side) {
        this.width = side;
        this.height = side;
    }

    // Custom width and height
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

Rectangle square = new Rectangle(5);       // 5x5
Rectangle rect = new Rectangle(4, 7);     // 4x7
```

---

## 5. Copy Constructor

A constructor that creates a new object by copying values from an existing object.

```java
public class Book {
    String title;
    String author;
    double price;

    // Parameterized constructor
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Copy constructor
    public Book(Book other) {
        this.title = other.title;
        this.author = other.author;
        this.price = other.price;
    }
}

Book original = new Book("Java Basics", "John", 29.99);
Book copy = new Book(original);

System.out.println(copy.title);  // Java Basics
System.out.println(copy.author); // John

// Modifying copy does NOT affect original
copy.price = 19.99;
System.out.println(original.price); // 29.99
System.out.println(copy.price);     // 19.99
```

### Deep Copy vs Shallow Copy

```java
public class Address {
    String city;
    String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    // Copy constructor for deep copy
    public Address(Address other) {
        this.city = other.city;
        this.state = other.state;
    }
}

public class Person {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow copy — shares the same Address object
    public Person(Person other, boolean shallow) {
        this.name = other.name;
        this.address = other.address; // same reference!
    }

    // Deep copy — creates a new Address object
    public Person(Person other) {
        this.name = other.name;
        this.address = new Address(other.address); // new object!
    }
}
```

| Copy Type    | Primitive Fields | Object Fields            |
|--------------|-----------------|--------------------------|
| Shallow Copy | Copies values   | Copies **reference** (shared) |
| Deep Copy    | Copies values   | Creates **new object** (independent) |

---

## 6. Constructor Overloading

Defining multiple constructors with **different parameter lists** in the same class.

```java
public class Product {
    String name;
    double price;
    int quantity;
    String category;

    // Constructor 1: all fields
    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Constructor 2: without category (default = "General")
    public Product(String name, double price, int quantity) {
        this(name, price, quantity, "General");
    }

    // Constructor 3: name and price only
    public Product(String name, double price) {
        this(name, price, 1, "General");
    }

    // Constructor 4: name only
    public Product(String name) {
        this(name, 0.0, 0, "Uncategorized");
    }
}

Product p1 = new Product("Laptop", 999.99, 5, "Electronics");
Product p2 = new Product("Mouse", 29.99, 10);
Product p3 = new Product("Book", 15.99);
Product p4 = new Product("Sample");
```

### Rules for Overloading

- Constructors must differ in **number**, **type**, or **order** of parameters
- Return type is not considered (constructors have no return type)
- Access modifiers can be different

---

## 7. Constructor Chaining (`this()`)

Calling one constructor from another **within the same class** using `this()`.

```java
public class Account {
    String owner;
    String type;
    double balance;
    double interestRate;

    public Account(String owner, String type, double balance, double interestRate) {
        this.owner = owner;
        this.type = type;
        this.balance = balance;
        this.interestRate = interestRate;
        System.out.println("Account created for: " + owner);
    }

    public Account(String owner, String type, double balance) {
        this(owner, type, balance, 0.05); // default interest rate
    }

    public Account(String owner, String type) {
        this(owner, type, 0.0); // default balance
    }

    public Account(String owner) {
        this(owner, "Savings"); // default type
    }
}

Account a = new Account("Alice");
// Calls: Account("Alice") → Account("Alice","Savings") → Account("Alice","Savings",0.0) → Account("Alice","Savings",0.0,0.05)
```

### Rules for `this()`

1. Must be the **first statement** in the constructor
2. Cannot be used in a loop or conditional
3. **Only one** `this()` call per constructor (prevents recursion)
4. Cannot create circular chaining (compile error)

```java
// INVALID — circular chaining
public Foo() { this(1); }
public Foo(int x) { this(); } // compile error: recursive constructor invocation
```

---

## 8. Constructor Chaining with Inheritance (`super()`)

Calling the **parent class constructor** from a child class using `super()`.

```java
class Vehicle {
    String brand;
    int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
        System.out.println("Vehicle constructor called");
    }
}

class Car extends Vehicle {
    int doors;

    public Car(String brand, int year, int doors) {
        super(brand, year); // calls Vehicle(String, int)
        this.doors = doors;
        System.out.println("Car constructor called");
    }
}

Car car = new Car("Toyota", 2024, 4);
// Output:
// Vehicle constructor called
// Car constructor called
```

### Execution Order in Inheritance

```java
class A {
    A() { System.out.println("A constructor"); }
}
class B extends A {
    B() { System.out.println("B constructor"); }
}
class C extends B {
    C() { System.out.println("C constructor"); }
}

C obj = new C();
// Output:
// A constructor  ← parent first
// B constructor
// C constructor  ← child last
```

### Rules for `super()`

1. Must be the **first statement** in the constructor
2. Cannot use both `this()` and `super()` in the same constructor
3. If not explicitly called, Java inserts `super()` (no-arg) automatically
4. If parent has no no-arg constructor, you **must** call `super(args)` explicitly

```java
class Parent {
    Parent(int x) { } // no no-arg constructor
}

class Child extends Parent {
    Child() {
        super(10); // REQUIRED — Parent has no no-arg constructor
    }
}
```

---

## 9. Private Constructor

A constructor declared as `private` — prevents external instantiation.

### Use Cases

#### Singleton Pattern

```java
public class Database {
    private static Database instance;

    // Private constructor — cannot use 'new' from outside
    private Database() {
        System.out.println("Database connection created");
    }

    // Controlled access point
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}

// Database db = new Database();    // compile error
Database db = Database.getInstance(); // works
```

#### Utility Class (Only Static Methods)

```java
public class MathUtils {
    // Prevent instantiation
    private MathUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}

MathUtils.factorial(5); // works
// new MathUtils();     // compile error
```

#### Factory Pattern

```java
public class Shape {
    private String type;

    private Shape(String type) {
        this.type = type;
    }

    // Factory methods
    public static Shape createCircle() {
        return new Shape("Circle");
    }

    public static Shape createSquare() {
        return new Shape("Square");
    }
}

Shape circle = Shape.createCircle();
Shape square = Shape.createSquare();
```

---

## 10. Constructor vs Method

| Feature           | Constructor                        | Method                            |
|-------------------|------------------------------------|-----------------------------------|
| Purpose           | Initialize an object               | Perform an action                 |
| Name              | Must match class name              | Any valid identifier              |
| Return type       | None (not even void)               | Must have a return type           |
| Invocation        | Automatically with `new`           | Explicitly called                 |
| Inheritance       | Not inherited                      | Can be inherited                  |
| `this()`/`super()`| Can use as first statement        | Cannot use `this()` or `super()` |
| `static`          | Cannot be static                   | Can be static                     |
| `final`           | Cannot be final                    | Can be final                      |
| Overloading       | Yes                                | Yes                               |
| Overriding        | No (not inherited)                 | Yes                               |

---

## 11. Common Mistakes

### Mistake 1: Adding a Return Type

```java
// WRONG — this is a method, not a constructor!
public void Student() {
    this.name = "Unknown";
}

// CORRECT
public Student() {
    this.name = "Unknown";
}
```

### Mistake 2: Forgetting to Call `super()` When Needed

```java
class Parent {
    Parent(String name) { }
}

class Child extends Parent {
    // COMPILE ERROR: no default constructor in Parent
    Child() { }

    // CORRECT
    Child() {
        super("default");
    }
}
```

### Mistake 3: Assuming Default Constructor Always Exists

```java
class Person {
    Person(String name) { } // only parameterized constructor
}

// COMPILE ERROR: no default constructor
Person p = new Person();

// CORRECT
Person p = new Person("Alice");
```

### Mistake 4: Using `this()` and `super()` Together

```java
// COMPILE ERROR: cannot have both
public Child(int x) {
    super();    // first statement
    this(x, 0); // also wants to be first statement — ERROR
}
```

---

## 12. Best Practices

1. **Always provide a no-arg constructor** if you define parameterized constructors (needed for frameworks like Spring, Hibernate)

2. **Use constructor chaining** to avoid code duplication
   ```java
   // Good: one constructor with full logic, others delegate
   public Employee(String name, int id, double salary) { ... }
   public Employee(String name, int id) { this(name, id, 30000.0); }
   ```

3. **Validate parameters** in constructors
   ```java
   public Account(double balance) {
       if (balance < 0) {
           throw new IllegalArgumentException("Balance cannot be negative");
       }
       this.balance = balance;
   }
   ```

4. **Make fields `final`** when possible — set them in the constructor and never change
   ```java
   public class ImmutablePoint {
       private final int x;
       private final int y;

       public ImmutablePoint(int x, int y) {
           this.x = x;
           this.y = y;
       }
   }
   ```

5. **Use private constructors** for utility classes and singletons

6. **Prefer fewer constructor parameters** — consider the Builder pattern for many fields
   ```java
   User user = new User.Builder("Alice")
       .age(25)
       .email("alice@example.com")
       .build();
   ```
