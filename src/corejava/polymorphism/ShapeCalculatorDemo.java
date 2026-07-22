package corejava.polymorphism;

public class ShapeCalculatorDemo {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Circle(5);
        shapes[1] = new Rectangle(4, 6);
        shapes[2] = new Square(3);
        shapes[3] = new Triangle(4, 5);

        for (Shape shape : shapes) {
            shape.showDetails();
            System.out.println("==================");
        }

        ShapeCalculator calculator = new ShapeCalculator();
        System.out.println("Circle Area     : " + calculator.calculateArea(7));
        System.out.println("Rectangle Area  : " + calculator.calculateArea(4, 6));    
        System.out.println("Square Area     : " + calculator.calculateArea(3));
        System.out.println("Triangle Area   : " + calculator.calculateArea(4, 5, true));
    }
}
