package corejava.constructors;

public class Author {
    String name;
    String email;
    Address address;

    // no argument constructor
    public Author() {
        this.name = "Unknown";
        this.email = "Unknown";
        this.address = new Address();
        System.out.println("Author object created");
    }
    // parameterized constructor
    public Author(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
        System.out.println("Author object created with name: " + name);
    }
}
