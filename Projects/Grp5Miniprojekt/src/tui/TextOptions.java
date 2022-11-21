package tui;

import java.util.ArrayList;

/**
 * Description of TextOptions goes here.
 * 
 * @author knol, mhi
 * @version 2018.10.19
 */
public class TextOptions {
    // instance variables
    private ArrayList<String> options;
    private String title;
    private boolean cancellable;

    /**
     * Constructor for objects of TextOptions
     */
    public TextOptions(String title, String cancelText) {
        // initialise instance variables
        options = new ArrayList<>();
        this.title = title;
        if (cancelText != null) {
            cancellable = true;
        }
        addOption(cancelText);
    }

    // A constructor that calls the other constructor with the same name.
    public TextOptions(String title) {
        this(title, null);
    }

    /**
     * Add an option to the list of options.
     * 
     * @param option The option to add to the list of options.
     */
    public void addOption(String option) {
        options.add(option);
    }

    /**
     * It prints the title and all the options, then prompts the user to input a
     * number, and returns that
     * number
     * 
     * @return The index of the option chosen.
     */
    public int prompt() {
        System.out.println(title);
        int size = options.size();
        for (int i = 0; i < size; i++) {
            String currentOption = options.get(i);
            if (currentOption != null) {
                System.out.println(" [" + i + "]\t" + currentOption);
            }
        }
        int choice = -1;
        int lowerBound = 0;
        if (!cancellable) {
            lowerBound = 1;
        }
        while (choice < lowerBound || choice >= size) {
            choice = TextInput.inputNumber("Vælg et tal");
        }
        return choice;
    }
}
