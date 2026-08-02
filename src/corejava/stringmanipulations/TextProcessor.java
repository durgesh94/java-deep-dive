package corejava.stringmanipulations;

import java.util.LinkedHashMap;
import java.util.Map;

public class TextProcessor {
    private final String originalText;

    public TextProcessor(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.originalText = text;
    }

    // 1. Reverse the string
    public String reverse() {
        return new StringBuilder(originalText).reverse().toString();
    }

    // 2. Check palindrome
    public boolean isPalindrome() {
        String cleaned = originalText.toLowerCase().replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    // 3. Capitalize each word (Title Case)
    public String toTitleCase() {
        StringBuilder sb = new StringBuilder();
        String[] words = originalText.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase());
            }
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // 4. Remove duplicate characters, preserve order
    public String removeDuplicates() {
        StringBuilder sb = new StringBuilder();
        boolean[] seen = new boolean[256]; // ASCII

        for (char c : originalText.toCharArray()) {
            if (!seen[c]) {
                seen[c] = true;
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 5. Count word frequency
    public Map<String, Integer> wordFrequency() {
        Map<String, Integer> freq = new LinkedHashMap<>();
        String[] words = originalText.toLowerCase().split("\\s+");

        for (String word : words) {
            String clean = word.replaceAll("[^a-z0-9]", "");
            if (!clean.isEmpty()) {
                freq.merge(clean, 1, Integer::sum);
            }
        }
        return freq;
    }

    // 6. Mask sensitive part of a string (e.g. email, card number)
    public String mask(int visibleStart, int visibleEnd, char maskChar) {
        if (originalText.length() <= visibleStart + visibleEnd) {
            return originalText; // too short to mask meaningfully
        }
        StringBuilder sb = new StringBuilder(originalText);
        for (int i = visibleStart; i < sb.length() - visibleEnd; i++) {
            sb.setCharAt(i, maskChar);
        }
        return sb.toString();
    }

    // 7. Build a formatted summary using conditional appends
    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Length: ").append(originalText.length()).append("\n");
        sb.append("Words: ").append(originalText.trim().split("\\s+").length).append("\n");
        sb.append("Palindrome: ").append(isPalindrome()).append("\n");
        sb.append("Uppercase: ").append(originalText.toUpperCase());
        return sb.toString();
    }
}
