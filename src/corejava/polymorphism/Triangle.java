package corejava.polymorphism;

public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle() {
        super();
        this.base = 1;
        this.height = 1;
    }

    public Triangle(double b, double h) {
        super("Yellow", "Triangle");
        this.base = b;
        this.height = h;
    }

    public Triangle(String color, double b, double h) {
        super(color, "Triangle");
        this.base = b;
        this.height = h;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Base: " + base);
        System.out.println("Height: " + height);
        System.out.println("Area: " + calculateArea());
    }
}
