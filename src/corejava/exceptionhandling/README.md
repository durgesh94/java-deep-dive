# Exception Handling in Java

Exception handling is a mechanism to handle runtime errors so that normal program flow can continue safely.

## Table of Contents

- [1. What is an Exception?](#1-what-is-an-exception)
- [2. Why Exception Handling is Needed](#2-why-exception-handling-is-needed)
- [3. Exception Hierarchy](#3-exception-hierarchy)
- [4. Types of Exceptions](#4-types-of-exceptions)
- [5. try-catch](#5-try-catch)
- [6. Multiple catch Blocks](#6-multiple-catch-blocks)
- [7. finally Block](#7-finally-block)
- [8. try-with-resources](#8-try-with-resources)
- [9. throw Keyword](#9-throw-keyword)
- [10. throws Keyword](#10-throws-keyword)
- [11. Custom Exceptions](#11-custom-exceptions)
- [12. Exception Propagation](#12-exception-propagation)
- [13. Rethrowing Exceptions](#13-rethrowing-exceptions)
- [14. Best Practices](#14-best-practices)

---

## 1. What is an Exception?

An exception is an event that disrupts normal execution of a program.

```java
int a = 10;
int b = 0;
int result = a / b; // ArithmeticException
```

Without handling, the program terminates abruptly.

---

## 2. Why Exception Handling is Needed

- Prevents sudden program termination
- Allows graceful recovery
- Separates error-handling code from business logic
- Improves reliability and maintainability

---

## 3. Exception Hierarchy

```text
Object
 └── Throwable
	  ├── Error
	  │    ├── OutOfMemoryError
	  │    └── StackOverflowError
	  └── Exception
		   ├── IOException
		   ├── SQLException
		   └── RuntimeException
				├── NullPointerException
				├── ArithmeticException
				└── IllegalArgumentException
```

- `Error`: serious problems, usually not handled by application code
- `Exception`: conditions that applications can handle

---

## 4. Types of Exceptions

### Checked Exceptions

- Checked at compile time
- Must be handled using `try-catch` or declared with `throws`
- Examples: `IOException`, `SQLException`, `ClassNotFoundException`

### Unchecked Exceptions

- Occur at runtime
- Subclasses of `RuntimeException`
- Not required to be declared or caught
- Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`

---

## 5. try-catch

Use `try` for code that may throw exceptions, and `catch` to handle them.

```java
public class TryCatchExample {
	public static void main(String[] args) {
		try {
			int result = 10 / 0;
			System.out.println("Result: " + result);
		} catch (ArithmeticException e) {
			System.out.println("Cannot divide by zero: " + e.getMessage());
		}

		System.out.println("Program continues...");
	}
}
```

---

## 6. Multiple catch Blocks

Handle different exception types separately.

```java
try {
	String text = null;
	System.out.println(text.length());
	int[] arr = new int[2];
	System.out.println(arr[5]);
} catch (NullPointerException e) {
	System.out.println("Null value encountered");
} catch (ArrayIndexOutOfBoundsException e) {
	System.out.println("Array index is invalid");
} catch (Exception e) {
	System.out.println("General exception: " + e.getMessage());
}
```

Important: Catch specific exceptions before general ones.

---

## 7. finally Block

`finally` executes whether an exception occurs or not. It is used for cleanup.

```java
try {
	System.out.println("Inside try");
	int x = 10 / 2;
} catch (Exception e) {
	System.out.println("Inside catch");
} finally {
	System.out.println("Inside finally - always executes");
}
```

---

## 8. try-with-resources

Used to automatically close resources (Java 7+).

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			String line = br.readLine();
			System.out.println(line);
		} catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
		}
	}
}
```

Resource must implement `AutoCloseable`.

---

## 9. throw Keyword

`throw` is used to explicitly throw an exception.

```java
public class ThrowExample {
	static void validateAge(int age) {
		if (age < 18) {
			throw new IllegalArgumentException("Age must be 18 or above");
		}
		System.out.println("Valid age");
	}

	public static void main(String[] args) {
		validateAge(16);
	}
}
```

---

## 10. throws Keyword

`throws` declares that a method may throw checked exceptions.

```java
import java.io.*;

public class ThrowsExample {
	static void readFile() throws IOException {
		FileReader fr = new FileReader("input.txt");
		fr.close();
	}

	public static void main(String[] args) {
		try {
			readFile();
		} catch (IOException e) {
			System.out.println("Handled in main: " + e.getMessage());
		}
	}
}
```

---

## 11. Custom Exceptions

Create your own exception type for domain-specific errors.

```java
class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String message) {
		super(message);
	}
}

class BankAccount {
	private double balance;

	public BankAccount(double balance) {
		this.balance = balance;
	}

	public void withdraw(double amount) throws InsufficientBalanceException {
		if (amount > balance) {
			throw new InsufficientBalanceException("Not enough balance");
		}
		balance -= amount;
		System.out.println("Withdrawn: " + amount);
	}
}
```

---

## 12. Exception Propagation

If a method does not handle an exception, it propagates to the caller.

```java
public class PropagationExample {
	static void method3() {
		int x = 10 / 0;
	}

	static void method2() {
		method3();
	}

	static void method1() {
		method2();
	}

	public static void main(String[] args) {
		try {
			method1();
		} catch (ArithmeticException e) {
			System.out.println("Caught in main: " + e.getMessage());
		}
	}
}
```

---

## 13. Rethrowing Exceptions

Catch an exception, add context, and rethrow.

```java
public class RethrowExample {
	static void process() {
		try {
			String value = null;
			value.length();
		} catch (NullPointerException e) {
			throw new RuntimeException("Processing failed due to null input", e);
		}
	}

	public static void main(String[] args) {
		try {
			process();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}
```

---

## 14. Best Practices

1. Catch specific exceptions instead of broad `Exception` when possible.
2. Do not use exceptions for normal control flow.
3. Always log meaningful error details.
4. Use custom exceptions for business/domain errors.
5. Clean up resources using try-with-resources.
6. Preserve stack trace when rethrowing exceptions.
7. Add clear exception messages.

### Good vs Bad Example

```java
// Bad: swallows exception silently
try {
	riskyOperation();
} catch (Exception e) {
}

// Good: handles and logs clearly
try {
	riskyOperation();
} catch (IOException e) {
	System.out.println("Failed to read file: " + e.getMessage());
}
```

---

## Quick Summary

- `try`: wrap risky code
- `catch`: handle exceptions
- `finally`: cleanup always
- `throw`: manually raise exception
- `throws`: declare possible checked exceptions
- Custom exceptions improve business error handling
