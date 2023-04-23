package tui;

import java.util.Scanner;

/**
 * Description of TextInput goes here.
 * 
 * @author Istvan Knoll, Mogens Holm Iversen, Patrick Sørensen
 * @version 0.0.1 initial draft version
 * @version 0.0.2 added additional methods
 */
public class TextInput {
    // instance variables
    static Scanner sc;

    /**
     * Constructor for objects of TextInput
     */
    public TextInput() {
        // initialise instance variables
        sc = new Scanner(System.in);
    }

    /**
     * The function takes a string as input and returns an integer
     * 
     * @param question The question you want to ask the user.
     * @return A number
     */
    public static int inputNumber(String question) {
        printQuesiton(question);
        while (!sc.hasNextInt()) {
            System.out.println("Input skal være et tal - prøv igen");
            sc.nextInt();
        }
        return sc.nextInt();
    }

    /**
     * It creates a new Scanner object, reads the next line of input, and returns it
     * 
     * @return A string
     */
    public String getStringInput() {
        System.out.print("> ");
        return sc.nextLine();
    }

    /**
     * It takes no parameters, and returns an integer
     * 
     * @return The number that the user inputs.
     */
    public int getIntInput() {
        System.out.print("> ");
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }

    /**
     * It takes no arguments, and returns a double
     * 
     * @return The number that the user inputs.
     */
    public double getDoubleInput() {
        System.out.print("> ");
        String input = sc.nextLine();
        return Double.parseDouble(input);
    }

    /**
     * It takes no arguments, and returns a long
     * 
     * @return The next long value that the user inputs.
     */
    public long getLongInput() {
        System.out.print("> ");
        String input = sc.nextLine();
        return Long.parseLong(input);
    }

    /**
     * Prints a question to the console.
     * 
     * @param question The question to ask the user.
     */
    private static void printQuesiton(String question) {
        System.out.print(" " + question + ": ");
    }
}
