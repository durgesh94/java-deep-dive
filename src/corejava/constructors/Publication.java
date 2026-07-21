package corejava.constructors;

public class Publication {
    String publisherName;
    Address publisherAddress;
    int yearPublished;

    // no-arg constructor
    public Publication() {
        this.publisherName = "Unknown";
        this.publisherAddress = new Address();
        this.yearPublished = 0;
        System.out.println("Publication object created");
    }

    // parameterized constructor
    public Publication(String publisherName, Address publisherAddress, int yearPublished) {
        this.publisherName = publisherName;
        this.publisherAddress = publisherAddress;
        this.yearPublished = yearPublished;
        System.out.println("Publication object created with publisher: " + publisherName + ", year: " + yearPublished);
    }

    // parameterized constructor with publisher name only
    public Publication(String publisherName) {
        this(publisherName, new Address(), 0);
        System.out.println("Publication object created with publisher: " + publisherName);
    }
}
