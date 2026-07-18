# Loops in Java

Loops are used to execute a block of code repeatedly as long as a specified condition is true.

## Table of Contents

- [1. for Loop](#1-for-loop)
- [2. while Loop](#2-while-loop)
- [3. do-while Loop](#3-do-while-loop)
- [4. Enhanced for-each Loop](#4-enhanced-for-each-loop)
- [5. Nested Loops](#5-nested-loops)
- [6. Loop Control Statements](#6-loop-control-statements)
- [7. Labeled Loops](#7-labeled-loops)
- [8. Infinite Loops](#8-infinite-loops)
- [9. Common Pitfalls](#9-common-pitfalls)

---

## 1. for Loop

Used when the **number of iterations is known** beforehand.

### Syntax

```java
for (initialization; condition; update) {
    // code block
}
```

### How It Works

1. **Initialization** – executed once at the start
2. **Condition** – checked before each iteration; loop stops when `false`
3. **Code block** – executes if condition is `true`
4. **Update** – executed after each iteration

### Examples

```java
// Print numbers 1 to 5
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
// Output: 1 2 3 4 5

// Reverse loop
for (int i = 10; i >= 1; i--) {
    System.out.println(i);
}

// Multiple variables in for loop
for (int i = 0, j = 10; i < j; i++, j--) {
    System.out.println("i=" + i + ", j=" + j);
}

// Iterate over an array
int[] numbers = {10, 20, 30, 40, 50};
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

### Key Points

- All three parts (initialization, condition, update) are **optional**
- Variables declared in initialization are scoped to the loop
- Can declare multiple variables of the **same type** in initialization

---

## 2. while Loop

Used when the **number of iterations is unknown** and depends on a condition.

### Syntax

```java
while (condition) {
    // code block
}
```

### How It Works

1. **Condition** is evaluated **before** each iteration
2. If `true`, the code block executes
3. If `false`, the loop terminates
4. Also called a **pre-test loop** (condition checked first)

### Examples

```java
// Print numbers 1 to 5
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}

// Read input until user types "exit"
Scanner scanner = new Scanner(System.in);
String input = "";
while (!input.equals("exit")) {
    System.out.print("Enter command: ");
    input = scanner.nextLine();
    System.out.println("You entered: " + input);
}

// Sum of digits of a number
int num = 12345;
int sum = 0;
while (num > 0) {
    sum += num % 10;  // add last digit
    num /= 10;        // remove last digit
}
System.out.println("Sum of digits: " + sum); // Output: 15

// Reverse a number
int number = 1234;
int reversed = 0;
while (number != 0) {
    reversed = reversed * 10 + number % 10;
    number /= 10;
}
System.out.println("Reversed: " + reversed); // Output: 4321
```

### Key Points

- If the condition is `false` initially, the loop body **never executes**
- Make sure the condition eventually becomes `false` to avoid infinite loops
- Commonly used for input validation and sentinel-controlled loops

---

## 3. do-while Loop

Similar to `while`, but the code block executes **at least once** because the condition is checked **after** execution.

### Syntax

```java
do {
    // code block
} while (condition);  // note the semicolon
```

### How It Works

1. **Code block** executes first
2. **Condition** is evaluated after execution
3. If `true`, the loop repeats; if `false`, it terminates
4. Also called a **post-test loop** (condition checked after)

### Examples

```java
// Print numbers 1 to 5
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);

// Menu-driven program
Scanner scanner = new Scanner(System.in);
int choice;
do {
    System.out.println("\n--- Menu ---");
    System.out.println("1. Add");
    System.out.println("2. Subtract");
    System.out.println("3. Exit");
    System.out.print("Enter choice: ");
    choice = scanner.nextInt();

    switch (choice) {
        case 1: System.out.println("Addition selected"); break;
        case 2: System.out.println("Subtraction selected"); break;
        case 3: System.out.println("Exiting..."); break;
        default: System.out.println("Invalid choice!");
    }
} while (choice != 3);

// Input validation (ensure positive number)
int number;
do {
    System.out.print("Enter a positive number: ");
    number = scanner.nextInt();
} while (number <= 0);
```

### Key Points

- Guarantees **at least one execution** of the loop body
- Don't forget the **semicolon** after `while(condition);`
- Best suited for **menu-driven programs** and **input validation**

---

## 4. Enhanced for-each Loop

Introduced in **Java 5**. Used to iterate over arrays and collections without an index variable.

### Syntax

```java
for (dataType element : arrayOrCollection) {
    // use element
}
```

### Examples

```java
// Iterate over an array
int[] numbers = {10, 20, 30, 40, 50};
for (int num : numbers) {
    System.out.println(num);
}

// Iterate over a String array
String[] fruits = {"Apple", "Banana", "Cherry"};
for (String fruit : fruits) {
    System.out.println(fruit);
}

// Iterate over an ArrayList
List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
for (String name : names) {
    System.out.println(name);
}

// Iterate over a 2D array
int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
for (int[] row : matrix) {
    for (int val : row) {
        System.out.print(val + " ");
    }
    System.out.println();
}
```

### Key Points

- **Read-only** – you cannot modify the array/collection elements through the loop variable
- No access to the **index** of the current element
- Works with any object that implements `Iterable` interface
- Cleaner and less error-prone than traditional `for` loop for simple iteration
- Cannot iterate **backwards** or skip elements

---

## 5. Nested Loops

A loop inside another loop. The inner loop completes all iterations for each iteration of the outer loop.

### Examples

```java
// Multiplication table (1-5)
for (int i = 1; i <= 5; i++) {
    for (int j = 1; j <= 10; j++) {
        System.out.print(i * j + "\t");
    }
    System.out.println();
}

// Right-angled triangle pattern
// *
// * *
// * * *
// * * * *
// * * * * *
for (int i = 1; i <= 5; i++) {
    for (int j = 1; j <= i; j++) {
        System.out.print("* ");
    }
    System.out.println();
}

// Inverted triangle pattern
// * * * * *
// * * * *
// * * *
// * *
// *
for (int i = 5; i >= 1; i--) {
    for (int j = 1; j <= i; j++) {
        System.out.print("* ");
    }
    System.out.println();
}

// Pyramid pattern
//     *
//    * *
//   * * *
//  * * * *
// * * * * *
int rows = 5;
for (int i = 1; i <= rows; i++) {
    for (int j = rows - i; j > 0; j--) {
        System.out.print(" ");
    }
    for (int k = 1; k <= i; k++) {
        System.out.print("* ");
    }
    System.out.println();
}

// Number pattern
// 1
// 1 2
// 1 2 3
// 1 2 3 4
// 1 2 3 4 5
for (int i = 1; i <= 5; i++) {
    for (int j = 1; j <= i; j++) {
        System.out.print(j + " ");
    }
    System.out.println();
}
```

### Key Points

- **Time complexity** of nested loops is typically O(n²) for two levels
- Inner loop runs `n × m` times (outer iterations × inner iterations)
- Commonly used for patterns, matrices, and multi-dimensional arrays

---

## 6. Loop Control Statements

### break Statement

Terminates the loop immediately and transfers control to the statement after the loop.

```java
// Exit loop when value is found
int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
for (int num : numbers) {
    if (num == 5) {
        System.out.println("Found 5!");
        break;  // exits the loop
    }
    System.out.println(num);
}
// Output: 1 2 3 4 Found 5!
```

### continue Statement

Skips the current iteration and moves to the next iteration of the loop.

```java
// Skip even numbers
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) {
        continue;  // skip even numbers
    }
    System.out.println(i);
}
// Output: 1 3 5 7 9
```

### return Statement

Exits the entire method (not just the loop).

```java
public static int findFirst(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;  // exits the method, returning the index
        }
    }
    return -1;  // not found
}
```

### Comparison

| Statement  | Effect                                          |
|------------|--------------------------------------------------|
| `break`    | Exits the **current loop** entirely              |
| `continue` | Skips to the **next iteration** of current loop  |
| `return`   | Exits the **entire method**                      |

---

## 7. Labeled Loops

Labels allow `break` and `continue` to target a **specific outer loop** in nested loops.

### Syntax

```java
labelName:
for (...) {
    for (...) {
        break labelName;    // breaks the outer loop
        continue labelName; // continues the outer loop
    }
}
```

### Examples

```java
// Break out of outer loop
outer:
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        if (i == 2 && j == 2) {
            break outer;  // exits both loops
        }
        System.out.println("i=" + i + ", j=" + j);
    }
}
// Output: i=1,j=1  i=1,j=2  i=1,j=3  i=2,j=1

// Continue outer loop
outer:
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        if (j == 2) {
            continue outer;  // skips to next iteration of outer loop
        }
        System.out.println("i=" + i + ", j=" + j);
    }
}
// Output: i=1,j=1  i=2,j=1  i=3,j=1
```

---

## 8. Infinite Loops

Loops that run forever (until externally terminated or a `break` is encountered).

```java
// Infinite for loop
for (;;) {
    // runs forever
}

// Infinite while loop
while (true) {
    // runs forever
}

// Infinite do-while loop
do {
    // runs forever
} while (true);
```

### Use Cases

- Server listening loops
- Game loops
- Event-driven programs
- Use `break` or `return` to exit when a condition is met

---

## 9. Common Pitfalls

### Off-by-One Errors

```java
// Wrong: misses last element
for (int i = 0; i < arr.length - 1; i++) { } // skips last element

// Correct
for (int i = 0; i < arr.length; i++) { }
```

### Modifying Collection During Iteration

```java
// Wrong: ConcurrentModificationException
List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
for (String s : list) {
    if (s.equals("b")) {
        list.remove(s);  // throws exception
    }
}

// Correct: use Iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().equals("b")) {
        it.remove();  // safe removal
    }
}
```

### Forgetting to Update Loop Variable

```java
// Wrong: infinite loop
int i = 0;
while (i < 10) {
    System.out.println(i);
    // forgot i++ → infinite loop!
}
```

---

## Loop Comparison Summary

| Feature              | for           | while         | do-while      | for-each      |
|----------------------|---------------|---------------|---------------|---------------|
| Known iterations     | Best choice   | Can use       | Can use       | Best for collections |
| Unknown iterations   | Can use       | Best choice   | Best choice   | N/A           |
| Min executions       | 0             | 0             | **1**         | 0             |
| Index access         | Yes           | Yes           | Yes           | No            |
| Modify elements      | Yes           | Yes           | Yes           | No            |
| Readability          | Good          | Good          | Good          | Best          |
| Use case             | Counting      | Condition-based | Menu/validation | Iteration   |
