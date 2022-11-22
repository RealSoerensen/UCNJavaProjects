package Exercises.ExceptionExercise;

import java.util.Scanner;

public class MenuInput {
    Scanner scanner;

    public MenuInput() {
        scanner = new Scanner(System.in);
    }

    public int getIntChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public String getStringChoice() {
        String choice = scanner.nextLine();
        return choice;
    }

    public double getDoubleChoice() {
        double choice = scanner.nextDouble();
        scanner.nextLine();
        return choice;
    }

    public void closeScanner() {
        scanner.close();
    }

}
