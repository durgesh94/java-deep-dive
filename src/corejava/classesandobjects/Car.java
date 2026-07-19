package corejava.classesandobjects;

public class Car {
    String brand;
    String model;
    int year;
    String color;
    double price;
    int mileage;
    int engineCapacity;
    int numberOfDoors;
    int seatingCapacity;
    int fuelTankCapacity;
    int topSpeed;

    // Constructor to initialize car details
    public Car(String brand, String model, int year, String color, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.mileage = 0;
        this.engineCapacity = 0;
        this.numberOfDoors = 0;
        this.seatingCapacity = 0;
        this.fuelTankCapacity = 0;
        this.topSpeed = 0;
    }

    // getter and setter methods for car attributes
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Method to update car details
    void updateDetails(int mileage, int engineCapacity, int numberOfDoors, int seatingCapacity, int fuelTankCapacity, int topSpeed) {
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.numberOfDoors = numberOfDoors;
        this.seatingCapacity = seatingCapacity;
        this.fuelTankCapacity = fuelTankCapacity;
        this.topSpeed = topSpeed;
    }

    // Method to display car details
    void displayDetails() {
        System.out.println("===========Car Details============");
        System.out.println("Brand               : " + brand);
        System.out.println("Model               : " + model);
        System.out.println("Year                : " + year);
        System.out.println("Color               : " + color);
        System.out.println("Price               : " + price);
        System.out.println("Mileage             : " + mileage);
        System.out.println("Engine Capacity     : " + engineCapacity);
        System.out.println("Number of Doors     : " + numberOfDoors);
        System.out.println("Seating Capacity    : " + seatingCapacity);
        System.out.println("Fuel Tank Capacity  : " + fuelTankCapacity);
        System.out.println("Top Speed           : " + topSpeed);
    }
}
