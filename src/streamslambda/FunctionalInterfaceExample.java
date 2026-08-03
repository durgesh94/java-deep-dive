package streamslambda;

public class FunctionalInterfaceExample {

    // Custom functional interface — exactly one abstract method
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);

        // default methods are allowed, don't break the "single abstract method" rule
        default Calculator andThenPrint() {
            return (a, b) -> {
                int result = this.calculate(a, b);
                System.out.println("Result: " + result);
                return result;
            };
        }
    }

    public static void main(String[] args) {

        // Different lambdas implementing the SAME interface
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        };

        System.out.println("Add: " + add.calculate(10, 5));
        System.out.println("Subtract: " + subtract.calculate(10, 5));
        System.out.println("Multiply: " + multiply.calculate(10, 5));
        System.out.println("Divide: " + divide.calculate(10, 5));

        // Passing a lambda where the interface is expected — method parameter
        runOperation(10, 5, (a, b) -> a % b);

        // Using the default method
        Calculator loud = add.andThenPrint();
        loud.calculate(7, 3);
    }

    // Method accepting a functional interface as a parameter
    static void runOperation(int a, int b, Calculator op) {
        System.out.println("Custom op result: " + op.calculate(a, b));
    }
}
