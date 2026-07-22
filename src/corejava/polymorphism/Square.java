package corejava.polymorphism;

public class Square extends Shape {
    private double side;

    public Square() {
        super();
        this.side = 1;
    }

    public Square(double s) {
        super("Green", "Square");
        this.side = s;
    }

    public Square(String color, double s) {
        super(color, "Square");
        this.side = s;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Side: " + side);
        System.out.println("Area: " + calculateArea());
    }
}
