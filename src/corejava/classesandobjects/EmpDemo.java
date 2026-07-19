package corejava.classesandobjects;

public class EmpDemo {
    public static void main(String[] args) {
        // Create an instance of Employee
        Employee emp1 = new Employee();
        emp1.id = 101;
        emp1.name = "Durgesh Tambe";
        emp1.age = 30;
        emp1.department = "IT";
        emp1.salary = 50000;
        emp1.experience = 8;

        // Display employee details
        emp1.displayDetails();

        // Calculate and display bonus
        emp1.calculateBonus(4);

        // Create another instance of Employee
        Employee emp2 = new Employee();
        emp2.id = 102;
        emp2.name = "Rahul Pawar";
        emp2.age = 25;
        emp2.department = "HR";
        emp2.salary = 40000;
        emp2.experience = 3;

        // Display employee details
        emp2.displayDetails();

        // Calculate and display bonus
        emp2.calculateBonus(3);

        // Create another instance of Employee
        Employee emp3 = new Employee();
        emp3.id = 103;
        emp3.name = "Anjali Wagh";
        emp3.age = 28;
        emp3.department = "Finance";
        emp3.salary = 60000;
        emp3.experience = 6;

        // Display employee details
        emp3.displayDetails();

        // Calculate and display bonus
        emp3.calculateBonus(5);

        // Display the total number of Employee objects created
        System.out.println("Total number of Employee objects created: " + Employee.getCount());
    }
}