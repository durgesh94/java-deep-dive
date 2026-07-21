package corejava.constructors;

public class Genre {
    static int nextId = 1; // auto incremented id for each genre object
    int id;
    String name;
    String description;

    // no argument constructor
    public Genre() {
        this.id = nextId++;
        this.name = "Unknown";
        this.description = "Unknown";
        System.out.println("Genre object created with id: " + this.id);
    }

    // parameterized constructor
    public Genre(String name, String description) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
        System.out.println("Genre object created with id: " + this.id + " and name: " + name);
    }
   
}
