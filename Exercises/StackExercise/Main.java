package Exercises.StackExercise;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("Hello");
        stack.push("World");
        stack.push("!");
        System.out.println(stack.peek());
        System.out.println(stack.pop());

        Stack<Integer> stack2 = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack2.push(i);
        }

        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop());
        }
    }
}
