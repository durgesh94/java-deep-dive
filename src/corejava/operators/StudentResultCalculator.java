package corejava.operators;

import java.util.Scanner;

// Example of the operators
public class StudentResultCalculator {
    public static void main(String[] args) {
        String studentName;
        int rollNumber, math, science, english, computer, history;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name:");
        studentName = scanner.nextLine();

        System.out.print("Enter roll number:");
        rollNumber = scanner.nextInt();

        System.out.print("Enter Math Marks: ");
        math = scanner.nextInt();

        System.out.print("Enter Science Marks: ");
        science = scanner.nextInt();

        System.out.print("Enter English Marks: ");
        english = scanner.nextInt();

        System.out.print("Enter Computer Marks: ");
        computer = scanner.nextInt();

        System.out.print("Enter History Marks: ");
        history = scanner.nextInt();

        scanner.close();

        System.out.println("=============Student Result=============");
        System.out.println("Student Name    : " + studentName);
        System.out.println("Roll Number     : " + rollNumber);
        System.out.println("Math            : " + math);
        System.out.println("Science         : " + science);
        System.out.println("English         : " + english);
        System.out.println("Computer        : " + computer);
        System.out.println("History         : " + history);

        int total = math + science + english + computer + history;
        int noOfSubjects = 5;
        float average =  (float) total / noOfSubjects;
        String result = average >= 35 ? "Passed" : "Failed";
        String grade = average >= 95 ? "A+" : average >= 90 ? "A" : average >= 80 ? "B+" : average >= 70 ? "B" : "C";

        System.out.println("-----------------------------------------");
        System.out.println("Total           : " + total);
        System.out.println("Average         : " + average);
        System.out.println("Percentage      : " + average+"%");
        System.out.println("Result          : " + result);
        System.out.println("Grade           : " + grade);
        System.out.println("=========================================");
    }
}
