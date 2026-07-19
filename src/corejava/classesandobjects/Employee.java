package corejava.classesandobjects;

public class Employee {
    // Static variable to keep track of the number of Employee objects created
    static int count = 0; // shared across all objects
    // Instance variables for employee details
    int id;
    String name;
    int age;
    String department;
    double salary;
    int experience;

    // Constructor to initialize employee details
     public Employee() {
        count++; // increments for every new object
    }

    // Static method to get the count of Employee objects created
    static int getCount() {
        return count;
    }

    // Method to display employee details
    void displayDetails(){
        System.out.println("=======Employee Details=======");
        System.out.println("ID          : "+id);
        System.out.println("Name        : "+name);
        System.out.println("Age         : "+age);
        System.out.println("Department  : "+department);
        System.out.println("Salary      : "+salary);
        System.out.println("Experiance  : "+experience);
    }

    // Method to calculate bonus based on experience and rating
    void calculateBonus(double rating){
        double bonus;
        if(experience >= 5){
            bonus = salary * rating / 100 * 0.75;
        } else {
            bonus = salary * rating / 100 * 0.25;
        }
        System.out.println("Bonus       : "+bonus);
    }
}
