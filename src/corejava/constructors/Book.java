package corejava.constructors;

public class Book extends Publication {
    String name;
    double price;
    Author author;
    Genre genre;

    // no argument constructor // default constructor
    public Book() {
        super(); // calls Publication() no-arg constructor
        this.name = "Unknown";
        this.price = 0.0;
        this.author = new Author();
        this.genre = new Genre();
        System.out.println("Book object created");
    }

    // parameterized constructor
    public Book(String name, Author author, Genre genre, double price, String publisherName, String publisherAddress, int yearPublished) {
        super(publisherName, new Address(), yearPublished); // calls Publication() with-arg constructor
        this.name = name;
        this.price = price;
        this.author = author;
        this.genre = genre;
        System.out.println("Book object created with name: " + name);
    }

    // copy constructor
    public Book(Book other) {
        super(other.publisherName, other.publisherAddress, other.yearPublished);
        this.name = other.name;
        this.price = other.price;
        this.author = other.author;
        this.genre = other.genre;
        System.out.println("Book object created by copying another book");
    }

    // deep copy constructor
    public Book(Book other, boolean deepCopy) {
        super(other.publisherName, other.publisherAddress, other.yearPublished);
        this.name = other.name;
        this.price = other.price;
        if (deepCopy) {
            this.author = new Author(other.author.name, other.author.email);
            this.genre = new Genre();
            this.genre.name = other.genre.name;
            this.genre.description = other.genre.description;
            System.out.println("Book object created by deep copying another book");
        } else {
            this.author = other.author;
            this.genre = other.genre;
            System.out.println("Book object created by shallow copying another book");
        }
    }

    // constructor overloading with all details
    public Book(String name, String authorName, String authorEmail, String genreName, String genreDescription, double price) {
        super(); // calls Publication() no-arg constructor
        this.name = name;
        this.price = price;
        this.author = new Author(authorName, authorEmail);
        this.genre = new Genre();
        this.genre.name = genreName;
        this.genre.description = genreDescription;
        System.out.println("Book object created with name: " + name + ", author: " + authorName + ", genre: " + genreName);
    }

    // constructor overloading with only name and price
    public Book(String name, double price) {
        super(); // calls Publication() no-arg constructor
        this.name = name;
        this.price = price;
        this.author = new Author();
        this.genre = new Genre();
        System.out.println("Book object created with name: " + name + " and price: " + price);
    }

    // constructor chaining example with name, author and genre
    public Book(String name, Author author, Genre genre) {
        this(name, author, genre, 0.0, "Unknown", "Unknown", 0); // calling parameterized constructor with price as 0.0
        System.out.println("Book object created with name: " + name + ", author: " + author.name + ", genre: " + genre.name);
    }

    // constructor chaining example with only name and author name
    public Book(String name, String authorName) {
        this(name, new Author(), new Genre(), 0.0, "Unknown", "Unknown", 0); // calling parameterized constructor
        this.author.name = authorName;
        System.out.println("Book object created with name: " + name + " and author: " + authorName);
    }

    // constructor chaining with inheritance (super() call with publisher name)
    public Book(String name, String authorName, String authorEmail) {
        super("Default Publisher"); // calls Publication(String) → sets publisherName
        this.name = name;
        this.price = 0.0;
        this.author = new Author(authorName, authorEmail);
        this.genre = new Genre();
        System.out.println("Book object created with name: " + name + ", author: " + authorName + ", email: " + authorEmail);
    }

    // constructor chaining with inheritance (super() call with full publisher details)
    public Book(String name, String authorName, String authorEmail, String genreName, String genreDescription) {
        super("Default Publisher", new Address(), 2024); // calls Publication(String, Address, int)
        this.name = name;
        this.price = 0.0;
        this.author = new Author(authorName, authorEmail);
        this.genre = new Genre();
        this.genre.name = genreName;
        this.genre.description = genreDescription;
        System.out.println("Book object created with name: " + name + ", author: " + authorName + ", genre: " + genreName);
    }

    // constructor with publisher details (demonstrates full super() usage)
    public Book(String name, double price, String publisherName, Address publisherAddress, int yearPublished) {
        super(publisherName, publisherAddress, yearPublished); // calls Publication(String, Address, int)
        this.name = name;
        this.price = price;
        this.author = new Author();
        this.genre = new Genre();
        System.out.println("Book object created with name: " + name + ", publisher: " + publisherName);
    }

    // private constructor — used with factory method pattern
    private Book(String name, double price, Author author, Genre genre, String publisherName) {
        super(publisherName);
        this.name = name;
        this.price = price;
        this.author = author;
        this.genre = genre;
        System.out.println("Book created via private constructor");
    }

    // static factory method — uses the private constructor
    public static Book createDefaultBook(String name) {
        return new Book(name, 0.0, new Author(), new Genre(), "Self Published");
    }

    // constructor with validation — throws exception for invalid input
    public static Book createBook(String name, double price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return new Book(name, price, new Author(), new Genre(), "Unknown Publisher");
    }
}
