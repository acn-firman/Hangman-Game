package org.firman;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * description of class Main 
 *
 * @author firman.lasaman
 * @version v1.0.0
 */
public class Main {
    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<Integer, String> wordList = new HashMap<>();
        wordList.put(1, "Animal");
        wordList.put(2, "Fruit");
        wordList.put(3, "Countries");
        wordList.put(4, "Movies");
        wordList.put(5, "Sports");
        Map<Integer, String[]> wordCategory = new HashMap<>();
        wordCategory.put(1,
                new String[] { "cat", "dog", "bird", "fish", "lion", "tiger", "elephant", "monkey", "snake", "bear" });
        wordCategory.put(2, new String[] { "apple", "banana", "orange", "grape", "mango", "melon", "strawberry",
                "watermelon", "pineapple", "pear" });
        wordCategory.put(3, new String[] { "indonesia", "malaysia", "singapore", "thailand", "vietnam", "philippines",
                "china", "japan", "korea", "india" });
        wordCategory.put(4, new String[] { "avengers", "spiderman", "batman", "superman", "aquaman", "wonderwoman",
                "ironman", "hulk", "thor", "captainamerica" });
        wordCategory.put(5, new String[] { "football", "basketball", "badminton", "tennis", "volleyball", "baseball",
                "golf", "rugby", "hockey", "cricket" });
        int categoryNumber;
        Scanner scanner = new Scanner(System.in);
        char playAgain;
        boolean isPlaying = false;
        do {

            System.out.println("Welcome to Hangman Game");
            System.out.println("Choose a word category: ");
            // iterate through wordList
            for (Map.Entry<Integer, String> entry : wordList.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            do {
                System.out.print("Enter the number of your chosen category: ");
                categoryNumber = scanner.nextInt();
                scanner.nextLine();
                if (categoryNumber > 0 && categoryNumber <= wordList.size()) {
                    break;
                }
                System.out.println("Invalid input. Please enter a number between 1 and " + wordList.size() + ".");
            } while (true);

            System.out.println("You have selected '" + wordList.get(categoryNumber) + ".' Let's begin!");
            System.out.println("Word to guess: ");
            String[] words = wordCategory.get(categoryNumber);
            String word = words[new Random().nextInt(words.length)];
            String asterisk = new String(new char[word.length()]).replace("\0", "_");

            System.out.println(asterisk + "( " + word.length() + " Letters)");
            int count = 0;
            while (count < 7) {
                System.out.print("Guess any letter in the word : ");

                char guess = scanner.next().charAt(0);
                scanner.nextLine();

                System.out.println("guess: " + guess);
                // check if word contain guess

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        System.out.println("Correct guess! You have " + (7 - count) + " more guesses.");
                        asterisk = asterisk.substring(0, i) + guess + asterisk.substring(i + 1);
                    }
                }

                // count if word not contain guess
                if (!word.contains(String.valueOf(guess))) {
                    System.out.println("count: " + count);
                    count++;
                    System.out.println("Wrong guess! You have " + (7 - count) + " more guesses.");
                }
                System.out.println("asterisk : " + asterisk);
                if (asterisk.equals(word)) {
                    break;
                }
            }
            if (asterisk.contains("_")) {
                System.out.println("You lose! The word is '" + word + "'.");
            } else {
                System.out.println("Congratulations! You have guessed the word correctly!");
            }

            do {
                System.out.print("Do you want to play again? (Y/N) : ");
                playAgain = scanner.next().charAt(0);
                scanner.nextLine();
                if (playAgain == 'Y' || playAgain == 'y') {
                    isPlaying = true;
                } else if (playAgain == 'N' || playAgain == 'n') {
                    isPlaying = false;
                    System.out.println("Thank you for playing!");
                    break;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            } while (!isPlaying);

        } while (isPlaying);
        scanner.close();
    }

}