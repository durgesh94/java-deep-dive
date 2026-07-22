package corejava.polymorphism;

public class ShapeCalculator {
    public double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }

    public double calculateArea(double length, double width) {
        return length * width;
    }

    public double calculateArea(int side) {
        return side * side;
    }

    public double calculateArea(double base, double height, boolean triangle) {
        return 0.5 * base * height;
    }
}
