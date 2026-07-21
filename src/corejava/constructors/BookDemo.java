package corejava.constructors;

public class BookDemo {
    public static void main(String[] args) {

        // Creating Book objects using default constructor
        Book book1 = new Book();
        // Creating Book objects using parameterized constructor
        Book book2 = new Book("Java Programming", "Vishal Patil", "vishal.patil@example.com", "Programming", "Books on Java programming");
        // Creating Book objects using parameterized constructor with publisher details
        Book book3 = new Book("Advanced Java", 29.99, "Tech Books Publishing", new Address("123 Tech Street", "Pune", "MH", "411033"), 2024);
        // Creating Book objects using constructor chaining with only name and author name
        Book book4 = new Book("Data Structures", "John Doe");
        // Creating Book objects using constructor chaining with all details
        Book book5 = new Book("Algorithms", "Jane Smith", "jane.smith@example.com", "Computer Science", "Books on algorithms");
        // Creating Book objects using constructor chaining with inheritance (super() call with publisher name)
        Book book6 = new Book("Design Patterns", "Robert Martin", "robert.martin@example.com");
    }
}
