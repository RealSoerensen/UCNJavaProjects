package Exercises.StackToQueue;

public class Main {
    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<String>();
        stack.enqueue("This");
        stack.enqueue("is");
        stack.enqueue("a");
        stack.enqueue("test");
        stack.enqueue("?");

        System.out.println(stack.dequeue());
        System.out.println(stack.dequeue());

        System.out.println(stack.isEmpty());
        stack.clear();
        System.out.println(stack.isEmpty());
    }
}
