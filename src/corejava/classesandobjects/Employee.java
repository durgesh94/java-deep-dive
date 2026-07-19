package corejava.classesandobjects;

public class Employee {
    int id;
    String name;
    int age;
    String department;
    double salary;
    int experiance;

    void displayDetails(){
        System.out.println("=======Employee Details=======");
        System.out.println("ID          : "+id);
        System.out.println("Name        : "+name);
        System.out.println("Age         : "+age);
        System.out.println("Department  : "+department);
        System.out.println("Salary      : "+salary);
        System.out.println("Experiance  : "+experiance);
    }

    void calculateBonus(double rating){
        double bonus;
        if(experiance >= 5){
            bonus = salary * rating / 100 * 0.75;
        } else {
            bonus = salary * rating / 100 * 0.25;
        }
        System.out.println("Bonus       : "+bonus);
    }
}
