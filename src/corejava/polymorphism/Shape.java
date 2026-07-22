package corejava.polymorphism;

public abstract class Shape {
    private String color;
    private String shapeName;

    public Shape(){
        this.color = "Black";
        this.shapeName = "Unknown";
    }
    public Shape(String color, String name){
        this.color = color;
        this.shapeName = name;
    }

    public String getColor(){
        return color;
    }

    public String getShapeName(){
        return shapeName;
    }

    public void showDetails(){
        System.out.println("Shape name  :"+ shapeName);
        System.out.println("Shape color :"+ color);
    }

    public abstract double calculateArea();
}
