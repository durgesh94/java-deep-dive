package corejava.exceptionhandling;

import java.util.Scanner;

public class ExceptionHandlingDemo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            // 1. ArithmeticException: Division by zero
            System.out.print("Enter a number 1: ");
            int number1 = scanner.nextInt();
            System.out.print("Enter a number 2: ");
            int number2 = scanner.nextInt();
            int result = number1 / number2;
            System.out.println("Result: " + result);

            // 2. ArrayIndexOutOfBoundsException: Accessing an invalid index in an array
            int[] numbers = { 1, 2, 3 };
            System.out.print("Enter an index to access (0-2): ");
            int index = scanner.nextInt();
            System.out.println("Value at index " + index + ": " + numbers[index]);

            // 3. NullPointerException: Calling a method on a null object
            String str = null;
            System.out.println("Length of the string: " + str.length());

            // 4. NumberFormatException: Converting a non-numeric string to a number
            System.out.print("Enter a number as a string: ");
            String strNumber = scanner.next();
            int parsedNumber = Integer.parseInt(strNumber);
            System.out.println("Parsed number: " + parsedNumber);

            // 5. ClassCastException: Casting an object to an incompatible type
            Object obj = new Integer(10);
            String strObj = (String) obj; // This will throw ClassCastException

        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid array index.");
        } catch (NullPointerException e) {
            System.out.println("Error: Attempted to call a method on a null object.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        } catch (ClassCastException e) {
            System.out.println("Error: Incompatible type casting.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Program execution completed.");
        }
    }
}
