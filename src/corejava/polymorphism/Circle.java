package corejava.polymorphism;

public class Circle extends Shape {
    private double radius;

    public Circle(){
        super();
        this.radius = 1;
    }

    public Circle(double r){
        super("Red", "Circle");
        this.radius = r;
    }

    public Circle(String color, double r){
        super(color, "Circle");
        this.radius = r;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + calculateArea());
    }
}
