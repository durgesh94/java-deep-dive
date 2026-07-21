package corejava.constructors;

public class Author {
    String name;
    String email;
    // no argument constructor
    public Author() {
        this.name = "Unknown";
        this.email = "Unknown";
        System.out.println("Author object created");
    }
    // parameterized constructor
    public Author(String name, String email) {
        this.name = name;
        this.email = email;
        System.out.println("Author object created with name: " + name);
    }
}
