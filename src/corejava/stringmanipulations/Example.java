package corejava.stringmanipulations;

public class Example {
    public static void main(String[] args) {
        String str = "Hello, World!";
        System.out.println("Original String: " + str);
        
        // Convert to uppercase
        String upperStr = str.toUpperCase();
        System.out.println("Uppercase String: " + upperStr);
        
        // Convert to lowercase
        String lowerStr = str.toLowerCase();
        System.out.println("Lowercase String: " + lowerStr);
        
        // Replace characters
        String replacedStr = str.replace("World", "Java");
        System.out.println("Replaced String: " + replacedStr);
        
        // Substring
        String substring = str.substring(7, 12);
        System.out.println("Substring: " + substring);
        
        // Length of the string
        int length = str.length();
        System.out.println("Length of the string: " + length);
    }
}