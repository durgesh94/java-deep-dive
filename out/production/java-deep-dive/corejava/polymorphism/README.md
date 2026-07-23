# Polymorphism in Java

**Polymorphism** means "many forms" — it allows objects to behave differently based on their actual type, even when accessed through a common reference type.

## Table of Contents

- [1. What is Polymorphism?](#1-what-is-polymorphism)
- [2. Compile-Time Polymorphism (Method Overloading)](#2-compile-time-polymorphism-method-overloading)
- [3. Runtime Polymorphism (Method Overriding)](#3-runtime-polymorphism-method-overriding)
- [4. Upcasting](#4-upcasting)
- [5. Downcasting](#5-downcasting)
- [6. `instanceof` Operator](#6-instanceof-operator)
- [7. Covariant Return Types](#7-covariant-return-types)
- [8. Polymorphism with Interfaces](#8-polymorphism-with-interfaces)
- [9. Dynamic Method Dispatch](#9-dynamic-method-dispatch)
- [10. Methods That Cannot Be Overridden](#10-methods-that-cannot-be-overridden)
- [11. Overloading vs Overriding](#11-overloading-vs-overriding)
- [12. Real-World Examples](#12-real-world-examples)
- [13. Best Practices](#13-best-practices)

---

## 1. What is Polymorphism?

Polymorphism allows a single interface/reference to represent different underlying types at runtime.

### Types of Polymorphism

```
Polymorphism
├── Compile-Time (Static) Polymorphism
│   └── Method Overloading
│   └── Operator Overloading (limited in Java: + for String)
└── Runtime (Dynamic) Polymorphism
    └── Method Overriding
```

### Simple Example

```java
Animal animal = new Dog();  // parent reference, child object
animal.makeSound();         // Output: "Bark!" (Dog's version, decided at runtime)

animal = new Cat();         // same reference, different object
animal.makeSound();         // Output: "Meow!" (Cat's version, decided at runtime)
```

---

## 2. Compile-Time Polymorphism (Method Overloading)

Same method name, **different parameter lists** within the same class. The compiler decides which method to call based on arguments.

### Rules for Overloading

1. Must have **different** number, type, or order of parameters
2. Return type alone is **not** sufficient to overload
3. Access modifiers can be different
4. Can throw different exceptions

### Examples

```java
public class MathUtils {
    // Different number of parameters
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Different parameter types
    public double add(double a, double b) {
        return a + b;
    }

    // Different order of parameters
    public String concat(String s, int n) {
        return s + n;
    }

    public String concat(int n, String s) {
        return n + s;
    }
}

MathUtils math = new MathUtils();
math.add(5, 3);         // calls add(int, int) → 8
math.add(5, 3, 2);      // calls add(int, int, int) → 10
math.add(2.5, 3.5);     // calls add(double, double) → 6.0
math.concat("Age: ", 25); // calls concat(String, int)
math.concat(25, " years"); // calls concat(int, String)
```

### Type Promotion in Overloading

When no exact match is found, Java promotes smaller types to larger ones:

```
byte → short → int → long → float → double
char → int
```

```java
public class Demo {
    void show(int a) { System.out.println("int: " + a); }
    void show(double a) { System.out.println("double: " + a); }
}

Demo d = new Demo();
d.show(5);     // int: 5 (exact match)
d.show(5.0);   // double: 5.0 (exact match)
d.show('A');   // int: 65 (char promoted to int)
d.show(5L);    // double: 5.0 (long promoted to double, no long version)
```

### Constructor Overloading

```java
public class Student {
    String name;
    int age;

    public Student() { this("Unknown", 0); }
    public Student(String name) { this(name, 0); }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---

## 3. Runtime Polymorphism (Method Overriding)

A subclass provides its **own implementation** of a method already defined in the parent class. The JVM decides which method to call at **runtime** based on the actual object type.

### Rules for Overriding

1. Method name and parameter list must be **exactly the same**
2. Return type must be the **same or covariant** (subtype)
3. Access modifier cannot be **more restrictive** (can be same or wider)
4. Cannot override `static`, `final`, or `private` methods
5. Must have an **IS-A relationship** (inheritance)
6. Constructor cannot be overridden

### Examples

```java
class Animal {
    public void makeSound() {
        System.out.println("Some generic sound");
    }

    public void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark! Bark!");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating bones");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }

    @Override
    public void eat() {
        System.out.println("Cat is eating fish");
    }
}

// Runtime polymorphism in action
Animal a1 = new Dog();
Animal a2 = new Cat();
Animal a3 = new Animal();

a1.makeSound();  // Bark! Bark!   (Dog's version)
a2.makeSound();  // Meow!         (Cat's version)
a3.makeSound();  // Some generic sound (Animal's version)
```

### Access Modifier Rules for Overriding

| Parent Method | Child Can Use                      |
|---------------|-------------------------------------|
| `public`      | `public` only                       |
| `protected`   | `protected` or `public`             |
| Default       | Default, `protected`, or `public`   |
| `private`     | Cannot be overridden                |

### Calling Parent's Method with `super`

```java
class Dog extends Animal {
    @Override
    public void makeSound() {
        super.makeSound();  // calls Animal's makeSound()
        System.out.println("Bark! Bark!");
    }
}
// Output:
// Some generic sound
// Bark! Bark!
```

---

## 4. Upcasting

Storing a **child object** in a **parent reference**. It's automatic (implicit) and always safe.

```java
// Upcasting — automatic, no cast needed
Animal animal = new Dog();     // Dog IS-A Animal
Object obj = new String("Hi"); // String IS-A Object

// Array of parent type holding different child objects
Animal[] animals = {
    new Dog(),
    new Cat(),
    new Dog()
};

// Polymorphic method calls
for (Animal a : animals) {
    a.makeSound();  // calls the actual object's version
}
```

### What You Can and Cannot Access After Upcasting

```java
class Dog extends Animal {
    public void fetch() {
        System.out.println("Fetching the ball");
    }
}

Animal animal = new Dog();  // upcasted
animal.makeSound();  // ✅ works — defined in Animal, overridden in Dog
animal.eat();        // ✅ works — defined in Animal
// animal.fetch();   // ❌ COMPILE ERROR — fetch() not in Animal reference type
```

> After upcasting, you can only call methods defined in the **reference type** (parent), but the **actual object's version** runs.

---

## 5. Downcasting

Converting a **parent reference** back to a **child type**. It's explicit (manual) and risky — can throw `ClassCastException`.

```java
Animal animal = new Dog();  // upcasted

// Downcasting — explicit cast required
Dog dog = (Dog) animal;     // ✅ works — actual object IS a Dog
dog.fetch();                // now we can access Dog-specific methods

// DANGEROUS downcasting
Animal animal2 = new Cat();
// Dog dog2 = (Dog) animal2;  // ❌ ClassCastException at RUNTIME!
```

### Safe Downcasting with `instanceof`

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.fetch();  // safe!
}
```

### Pattern Matching instanceof (Java 16+)

```java
// Combines instanceof check + casting in one step
if (animal instanceof Dog dog) {
    dog.fetch();  // 'dog' is already cast, no explicit cast needed
}
```

---

## 6. `instanceof` Operator

Checks whether an object is an instance of a specific class or interface.

```java
Dog dog = new Dog();
Animal animal = new Dog();

System.out.println(dog instanceof Dog);     // true
System.out.println(dog instanceof Animal);  // true (Dog IS-A Animal)
System.out.println(dog instanceof Object);  // true (everything IS-A Object)

System.out.println(animal instanceof Dog);  // true (actual object is Dog)
System.out.println(animal instanceof Cat);  // false

// null check
System.out.println(null instanceof Dog);    // false (null is never instance of anything)
```

### Practical Usage

```java
public void processAnimal(Animal animal) {
    if (animal instanceof Dog dog) {
        dog.fetch();
    } else if (animal instanceof Cat cat) {
        cat.purr();
    } else {
        animal.makeSound();
    }
}
```

---

## 7. Covariant Return Types

When overriding, the return type can be a **subtype** of the parent method's return type.

```java
class Animal {
    public Animal create() {
        return new Animal();
    }

    public Number getCount() {
        return 0;
    }
}

class Dog extends Animal {
    @Override
    public Dog create() {  // ✅ Dog is a subtype of Animal
        return new Dog();
    }

    @Override
    public Integer getCount() {  // ✅ Integer is a subtype of Number
        return 5;
    }
}

Dog dog = new Dog();
Dog newDog = dog.create();   // no casting needed!
Integer count = dog.getCount();
```

### Rules

- Return type must be a **subtype** (covariant), not a super type
- Only works with **object types**, not primitives
- `int` cannot be covariant to `long` (primitives are not subtypes)

---

## 8. Polymorphism with Interfaces

Interfaces enable polymorphism across **unrelated classes**.

```java
interface Payable {
    double calculatePay();
    String getDescription();
}

class Employee implements Payable {
    String name;
    double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public double calculatePay() { return salary; }

    @Override
    public String getDescription() { return "Employee: " + name; }
}

class Freelancer implements Payable {
    String name;
    double hourlyRate;
    int hours;

    public Freelancer(String name, double hourlyRate, int hours) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    @Override
    public double calculatePay() { return hourlyRate * hours; }

    @Override
    public String getDescription() { return "Freelancer: " + name; }
}

class Invoice implements Payable {
    String description;
    double amount;

    public Invoice(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public double calculatePay() { return amount; }

    @Override
    public String getDescription() { return "Invoice: " + description; }
}

// Polymorphism — process all Payable objects uniformly
Payable[] payables = {
    new Employee("Alice", 50000),
    new Freelancer("Bob", 100, 160),
    new Invoice("Office supplies", 2500)
};

double totalPayment = 0;
for (Payable p : payables) {
    System.out.println(p.getDescription() + " → $" + p.calculatePay());
    totalPayment += p.calculatePay();
}
System.out.println("Total: $" + totalPayment);
// Employee: Alice → $50000.0
// Freelancer: Bob → $16000.0
// Invoice: Office supplies → $2500.0
// Total: $68500.0
```

---

## 9. Dynamic Method Dispatch

The mechanism by which Java resolves overridden method calls at **runtime**. The JVM looks at the **actual object type** (not the reference type) to determine which method to execute.

```java
class Shape {
    void draw() { System.out.println("Drawing shape"); }
}

class Circle extends Shape {
    @Override
    void draw() { System.out.println("Drawing circle"); }
}

class Square extends Shape {
    @Override
    void draw() { System.out.println("Drawing square"); }
}

class Triangle extends Shape {
    @Override
    void draw() { System.out.println("Drawing triangle"); }
}

// Dynamic dispatch in action
Shape[] shapes = { new Circle(), new Square(), new Triangle() };

for (Shape s : shapes) {
    s.draw();  // JVM checks actual object type at runtime
}
// Drawing circle
// Drawing square
// Drawing triangle
```

### How It Works Internally

```
Reference Type: Shape      →  Compiler checks: does Shape have draw()? ✅
Actual Object:  Circle     →  JVM calls: Circle's draw() at runtime

┌──────────────┐          ┌──────────────────┐
│ Shape s      │─────────►│ Circle object     │
│ (reference)  │          │ draw() → "circle" │
└──────────────┘          └──────────────────┘
```

### Fields Are NOT Polymorphic

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";
}

Parent obj = new Child();
System.out.println(obj.name);  // "Parent" — fields are resolved at COMPILE time
```

> **Only methods** participate in runtime polymorphism. Fields and static methods are resolved at compile time.

---

## 10. Methods That Cannot Be Overridden

### `static` Methods (Method Hiding)

```java
class Parent {
    static void greet() { System.out.println("Hello from Parent"); }
}

class Child extends Parent {
    static void greet() { System.out.println("Hello from Child"); } // method HIDING, not overriding
}

Parent p = new Child();
p.greet();  // "Hello from Parent" — static methods resolved by reference type
```

### `final` Methods

```java
class Parent {
    final void important() {
        System.out.println("Cannot be overridden");
    }
}

class Child extends Parent {
    // void important() { }  // ❌ COMPILE ERROR: cannot override final method
}
```

### `private` Methods

```java
class Parent {
    private void secret() { System.out.println("Parent secret"); }

    public void callSecret() { secret(); }
}

class Child extends Parent {
    // This is a NEW method, not an override (parent method is invisible)
    private void secret() { System.out.println("Child secret"); }
}

Parent p = new Child();
p.callSecret();  // "Parent secret" — Child's secret() is unrelated
```

### Summary

| Method Type | Can Override? | Behavior                               |
|-------------|--------------|----------------------------------------|
| `static`    | ❌ (hiding)   | Resolved by reference type (compile time) |
| `final`     | ❌            | Compile error if attempted             |
| `private`   | ❌            | Not visible to child; child can define its own |
| Regular     | ✅            | Resolved by actual object type (runtime) |

---

## 11. Overloading vs Overriding

| Feature            | Overloading                       | Overriding                          |
|--------------------|-----------------------------------|--------------------------------------|
| Also called        | Compile-time polymorphism         | Runtime polymorphism                |
| Where              | Same class (or inherited)         | Parent-child (inheritance)          |
| Method name        | Same                              | Same                                |
| Parameters         | Must be **different**             | Must be **same**                    |
| Return type        | Can be different                  | Same or covariant (subtype)         |
| Access modifier    | Can be different                  | Same or wider (less restrictive)    |
| `static` methods   | Can be overloaded                 | Cannot be overridden (hiding)       |
| `private` methods  | Can be overloaded                 | Cannot be overridden                |
| `final` methods    | Can be overloaded                 | Cannot be overridden                |
| Binding            | Early binding (compile time)      | Late binding (runtime)              |
| Performance        | Slightly faster                   | Slightly slower (vtable lookup)     |

```java
// Overloading — same class, different params
class Printer {
    void print(String s) { System.out.println(s); }
    void print(int n) { System.out.println(n); }       // overloaded
    void print(String s, int n) { System.out.println(s + n); } // overloaded
}

// Overriding — parent-child, same signature
class Vehicle {
    void start() { System.out.println("Vehicle starting"); }
}
class Car extends Vehicle {
    @Override
    void start() { System.out.println("Car starting with key"); } // overridden
}
```

---

## 12. Real-World Examples

### Notification System

```java
abstract class Notification {
    String recipient;
    String message;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    abstract void send();

    void logNotification() {
        System.out.println("Notification sent to: " + recipient);
    }
}

class EmailNotification extends Notification {
    public EmailNotification(String recipient, String message) {
        super(recipient, message);
    }

    @Override
    void send() {
        System.out.println("Sending EMAIL to " + recipient + ": " + message);
    }
}

class SMSNotification extends Notification {
    public SMSNotification(String recipient, String message) {
        super(recipient, message);
    }

    @Override
    void send() {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}

class PushNotification extends Notification {
    public PushNotification(String recipient, String message) {
        super(recipient, message);
    }

    @Override
    void send() {
        System.out.println("Sending PUSH to " + recipient + ": " + message);
    }
}

// Polymorphic usage — send all notifications uniformly
Notification[] notifications = {
    new EmailNotification("alice@mail.com", "Welcome!"),
    new SMSNotification("+91-9876543210", "OTP: 1234"),
    new PushNotification("user_123", "New message received")
};

for (Notification n : notifications) {
    n.send();            // each calls its own version
    n.logNotification(); // shared behavior from parent
}
```

### Shape Calculator (Polymorphism + Overloading + Overriding)

```java
abstract class Shape {
    abstract double area();
    abstract double perimeter();

    // Overloaded method
    void display() {
        System.out.println("Area: " + area());
    }

    void display(boolean showPerimeter) {
        System.out.println("Area: " + area());
        if (showPerimeter) {
            System.out.println("Perimeter: " + perimeter());
        }
    }
}

class Circle extends Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }

    @Override
    double area() { return Math.PI * radius * radius; }

    @Override
    double perimeter() { return 2 * Math.PI * radius; }
}

class Rectangle extends Shape {
    double width, height;
    Rectangle(double w, double h) { width = w; height = h; }

    @Override
    double area() { return width * height; }

    @Override
    double perimeter() { return 2 * (width + height); }
}

// Usage
Shape[] shapes = { new Circle(5), new Rectangle(4, 6) };
for (Shape s : shapes) {
    s.display(true);  // polymorphism (overriding) + overloading
    System.out.println("---");
}
```

---

## 13. Best Practices

1. **Use `@Override` annotation** — catches errors at compile time if method signature doesn't match

2. **Program to the interface/parent type** — for flexibility
   ```java
   // Good — easy to swap implementations
   List<String> names = new ArrayList<>();

   // Avoid — tied to specific implementation
   ArrayList<String> names = new ArrayList<>();
   ```

3. **Prefer composition over inheritance** when you only need code reuse (not polymorphism)

4. **Don't overload methods ambiguously**
   ```java
   // Bad — confusing for callers
   void process(int a, long b) { }
   void process(long a, int b) { }
   // process(5, 5) → ambiguous!
   ```

5. **Use `instanceof` before downcasting** to avoid `ClassCastException`

6. **Design for extension** — if a method shouldn't be overridden, make it `final`

7. **Keep the Liskov Substitution Principle (LSP)** — subclass objects should be usable wherever parent objects are expected without breaking behavior

8. **Avoid overriding concrete methods** with completely different behavior — it violates the principle of least surprise
