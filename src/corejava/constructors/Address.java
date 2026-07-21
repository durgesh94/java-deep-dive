package corejava.constructors;

public class Address {
    String street;
    String city;
    String state;
    String zipCode;

    // no argument constructor
    public Address() {
        this.street = "Unknown";
        this.city = "Unknown";
        this.state = "Unknown";
        this.zipCode = "Unknown";
        System.out.println("Address object created");
    }

    // parameterized constructor
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        System.out.println("Address object created with street: " + street + ", city: " + city);
    }
}
