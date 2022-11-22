package Exercises.RPNCalculator;

import java.util.Scanner;

public class RPNCalculator {
    private LinkedStack<Double> stack;
    private Scanner scanner;
    private final String BYE = "bye";
    private final String PLUS = "+";
    private final String MINUS = "-";
    private final String MULTIPLY = "*";
    private final String DIVIDE = "/";
    private final String PLUSMINUS = "+/-";

    public static void main(String[] args) {
        RPNCalculator calc = new RPNCalculator();
        calc.start();
    }

    public RPNCalculator() {
        stack = new LinkedStack<Double>();
        scanner = new Scanner(System.in);
    }

    private void start() {
        System.out.println("Welcome to the RPN Calculator!");
        System.out.println("Enter your input, or type 'bye' to quit.");
        String input = scanner.nextLine();
        while (!input.toLowerCase().equals(BYE)) {
            System.out.println(number(input));
            input = scanner.nextLine();
        }
    }

    private double number(String input) {
        double result = 0;
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                result = calculate(stack.pop(), stack.pop(), token);
                stack.push(result);
            } else if (isPlusMinus(token)) {
                double operand = stack.pop();
                result = plusMinus(operand);
                stack.push(result);
            } else {
                System.out.println("Invalid input");
            }
        }

        return result;
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return token.equals(PLUS) || token.equals(MINUS) || token.equals(MULTIPLY) || token.equals(DIVIDE);
    }

    private boolean isPlusMinus(String token) {
        return token.equals(PLUSMINUS);
    }

    private double calculate(double operand1, double operand2, String operator) {
        double result = 0;
        switch (operator) {
            case PLUS:
                result = operand1 + operand2;
                break;
            case MINUS:
                result = operand1 - operand2;
                break;
            case MULTIPLY:
                result = operand1 * operand2;
                break;
            case DIVIDE:
                result = operand1 / operand2;
                break;
        }
        return result;
    }

    private double plusMinus(double operand) {
        return -operand;
    }

}
