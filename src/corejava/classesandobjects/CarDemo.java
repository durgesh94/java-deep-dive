package corejava.classesandobjects;

public class CarDemo {
    public static void main(String[] args) {
        // Create an instance of Car
        Car car1 = new Car("Toyota", "Camry", 2020, "Red", 2525000);
        car1.updateDetails(15, 2500, 4, 5, 15, 130);

        // Display car details
        System.out.println("=======Car Details=======");
        System.out.println("Brand             : " + car1.getBrand());
        System.out.println("Model             : " + car1.getModel());
        System.out.println("Year              : " + car1.getYear());    
        System.out.println("Color             : " + car1.getColor());
        // set color to white and display details again
        car1.setColor("White");
        car1.displayDetails();

        // Create another instance of Car // Tata : Nexon
        Car car2 = new Car("Tata", "Nexon", 2025, "White", 1350000);
        car2.updateDetails(18, 199, 4, 5, 44, 120);
        car2.displayDetails();
    }
}
