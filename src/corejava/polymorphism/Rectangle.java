package corejava.polymorphism;

public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle() {
        super();
        this.length = 1;
        this.width = 1;
    }

    public Rectangle(double l, double w) {
        super("Blue", "Rectangle");
        this.length = l;
        this.width = w;
    }

    public Rectangle(String color, double l, double w) {
        super(color, "Rectangle");
        this.length = l;
        this.width = w;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Length: " + length);
        System.out.println("Width: " + width);
        System.out.println("Area: " + calculateArea());
    }
    
}
