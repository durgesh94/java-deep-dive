package corejava.stringmanipulations;

public class Main {
    public static void main(String[] args) {
        TextProcessor tp = new TextProcessor("A man a plan a canal Panama");

        System.out.println(tp.reverse());
        // amanaP lanac a nalp a nam A

        System.out.println(tp.isPalindrome());
        // true

        System.out.println(tp.toTitleCase());
        // A Man A Plan A Canal Panama

        System.out.println(tp.removeDuplicates());
        // A manplcoP (order-preserved unique chars)

        System.out.println(tp.wordFrequency());
        // {a=3, man=1, plan=1, canal=1, panama=1}

        TextProcessor card = new TextProcessor("1234567812345678");
        System.out.println(card.mask(4, 4, '*'));
        // 1234********5678

        System.out.println(tp.summary());
        // Length: 28
        // Words: 6
        // Palindrome: true
        // Uppercase: A MAN A PLAN A CANAL PANAMA
    }
}
