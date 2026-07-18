package corejava.loops;

import java.util.Scanner;

public class QuizApplication {
    // This class represents a simple quiz application : in this app please cover
    // the all loops in java and also cover the nested loops in java and also cover
    // the enhanced for loop in java and also cover the while loop in java and also
    // cover the do while loop in java and also cover the for each loop in java and
    // also cover the break statement in java and also cover the continue statement
    // in java and also cover the return statement in java and also cover the switch
    // statement in java and also cover the if else statement in java and also cover
    // the ternary operator in java and also cover the try catch block in java and
    // also cover the throw statement in java and also cover the throws statement in
    // java and also cover the finally block in java and also cover the assert
    // statement in java and also cover the synchronized block in java and also
    // cover the volatile keyword in java and also cover the transient keyword in
    // java and also cover the native keyword in java and also cover the strictfp
    // keyword in java and also cover the default keyword in java and also cover the
    // instanceof operator in java.

    public static void main(String[] args) {
    // Step 1: variable declaration and initialization
    Scanner scanner = new Scanner(System.in);
    int score;
    int correctAnswers;
    int wrongAnswers;
    int totalQuestions = 5;
    int userAnswer;
    String retake;
    // Step 2: store the questions and answers in arrays
    String[] questions = {
            "1. What is the capital of France?\n1. Berlin\n2. Madrid\n3. Paris\n4. Rome",
            "2. What is the largest planet in our solar system?\n1. Earth\n2. Jupiter\n3. Mars\n4. Saturn",
            "3. What is the chemical symbol for water?\n1. H2O\n2. CO2\n3. O2\n4. NaCl",
            "4. Who wrote 'Romeo and Juliet'?\n1. William Shakespeare\n2. Charles Dickens\n3. Mark Twain\n4. Jane Austen",
            "5. What is the square root of 64?\n1. 6\n2. 7\n3. 8\n4. 9"
    };

    // do-while loop: allows the user to retake the quiz
    do {
    // Reset score and counters for each attempt
    score = 0;
    correctAnswers = 0;
    wrongAnswers = 0;

    System.out.println("\n========== QUIZ START ==========\n");

    // Step 3: for-loop to iterate through the questions
    for(int i = 0; i < totalQuestions; i++)
    {
        System.out.println(questions[i]);
        // Step 4: get user input for the answer
        System.out.print("Enter your answer (1-4): ");
        userAnswer = scanner.nextInt();
        // Step 5: switch statement to check the answer
        switch (i) {
            case 0:
                if (userAnswer == 3) {
                    System.out.println("Correct!");
                    score += 10;
                    correctAnswers++;
                } else {
                    System.out.println("Wrong! The correct answer is 3. Paris.");
                    wrongAnswers++;
                }
                break;
            case 1:
                if (userAnswer == 2) {
                    System.out.println("Correct!");
                    score += 10;
                    correctAnswers++;
                } else {
                    System.out.println("Wrong! The correct answer is 2. Jupiter.");
                    wrongAnswers++;
                }
                break;
            case 2:
                if (userAnswer == 1) {
                    System.out.println("Correct!");
                    score += 10;
                    correctAnswers++;
                } else {
                    System.out.println("Wrong! The correct answer is 1. H2O.");
                    wrongAnswers++;
                }
                break;
            case 3:
                if (userAnswer == 1) {
                    System.out.println("Correct!");
                    score += 10;
                    correctAnswers++;
                } else {
                    System.out.println("Wrong! The correct answer is 1. William Shakespeare.");
                    wrongAnswers++;
                }
                break;
            case 4:
                if (userAnswer == 3) {
                    System.out.println("Correct!");
                    score += 10;
                    correctAnswers++;
                } else {
                    System.out.println("Wrong! The correct answer is 3. 8.");
                    wrongAnswers++;
                }
                break;
            default:
                System.out.println("Invalid question number.");
                break;
        }
        System.out.println();
    }
    // Step 6: Calculate percentage and Grade
    double percentage = (double) score / (totalQuestions * 10) * 100;
    String grade;
    if (percentage >= 90) {
        grade = "A";
    } else if (percentage >= 80) {
        grade = "B";
    } else if (percentage >= 70) {
        grade = "C";
    } else if (percentage >= 60) {
        grade = "D";
    } else {
        grade = "F";
    }

    // Step 7: Display results
    System.out.println("\n--- Quiz Results ---");
    System.out.println("Total Questions: " + totalQuestions);
    System.out.println("Correct Answers: " + correctAnswers);
    System.out.println("Wrong Answers: " + wrongAnswers);
    System.out.println("Score: " + score + "/" + (totalQuestions * 10));
    System.out.println("Percentage: " + percentage + "%");
    System.out.println("Grade: " + grade);

    // Step 8: Ask user if they want to retake the quiz (do-while retry)
    System.out.print("\nWould you like to retake the quiz? (yes/no): ");
    scanner.nextLine(); // consume leftover newline
    retake = scanner.nextLine().trim().toLowerCase();

    } while (retake.equals("yes"));

    System.out.println("\nThank you for taking the quiz! Goodbye.");

    // Step 9: Close the scanner
    scanner.close();
    }
}
