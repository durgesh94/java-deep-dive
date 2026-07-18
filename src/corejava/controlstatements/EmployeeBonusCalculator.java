package corejava.controlstatements;

import java.util.Scanner;

public class EmployeeBonusCalculator {

    // Method to calculate bonus percentage based on experience
    public int getBonusPercentage(int experience) {
        if (experience < 2) {
            return 5;
        } else if (experience < 4) {
            return 10;
        } else if (experience < 9) {
            return 15;
        } else {
            return 20;
        }
    }

    // Method to calculate extra performance bonus based on rating
    public int getExtraPerformanceBonus(int rating) {
        if (rating < 2) {
            return 0;
        } else if (rating < 3) {
            return 1;
        } else if (rating < 4) {
            return 3;
        } else {
            return 5;
        }
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Declare variables to hold employee details
        String empName, empID, status;
        double empSalary = 0.0;
        int empExp = 0, empRating = 0;
        EmployeeBonusCalculator empCal = new EmployeeBonusCalculator();

        // Get employee details from user input
        System.out.println("Enter employee name:");
        empName = scanner.nextLine();
        System.out.println("Enter employee ID:");
        empID = scanner.nextLine();
        System.out.println("Enter employee experiance:");
        empExp = scanner.nextInt();
        System.out.println("Enter employee rating:");
        empRating = scanner.nextInt();
        System.out.println("Enter employee salary:");
        empSalary = scanner.nextDouble();

        // Calculate bonus and total salary
        double bonus = empCal.getBonusPercentage(empExp);
        double extraBonus = empCal.getExtraPerformanceBonus(empRating);
        status = empRating > 2 ? "Eligible for Bonus" : "Not Eligible for Bonus";
        double bonusAmt = empRating > 2 ? empSalary * bonus / 100 : 0;
        double totalSalary = empSalary + bonusAmt;

        // Ask user to 1. View employee details 2. View Bonus details 3. View Final
        // Salary details 4. Exit
        System.out.println("Select an option:");
        System.out.println("1. View Employee Details");
        System.out.println("2. View Bonus Details");
        System.out.println("3. View Final Salary Details");
        System.out.println("4. Exit");
        int option = scanner.nextInt();

        // Use a switch statement to handle the user's selection
        switch (option) {
            case 1:
                System.out.println("Employee ID         : " + empID);
                System.out.println("Employee Name       : " + empName);
                System.out.println("Annual Salary       : " + empSalary);
                System.out.println("Experiance          : " + empExp);
                System.out.println("Performance Rating  : " + empRating);
                break;
            case 2:
                if (empRating <= 2) {
                    System.out.println("Employee is not eligible for bonus.");
                    break;
                } else {
                    System.out.println("Bonus Percentage    : " + bonus);
                    System.out.println("Extra Bonus         : " + extraBonus);
                    System.out.println("Bonus Amount        : " + bonusAmt);
                }
                break;
            case 3:
                System.out.println("Final Salary        : " + totalSalary);
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option selected.");
        }
        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
