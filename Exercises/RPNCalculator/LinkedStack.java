package Exercises.RPNCalculator;

public class LinkedStack<T> implements StackIF<T> {
    private int size;
    private Node top;

    protected class Node {
        private T value;
        private Node next;

        public Node(T value, LinkedStack<T>.Node next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public LinkedStack<T>.Node getNext() {
            return next;
        }
    }

    public LinkedStack() {
        size = 0;
        top = null;
    }

    @Override
    public void push(T t) {
        Node newNode = new Node(t, top);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (top == null) {
            return null;
        }

        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (top == null) {
            return null;
        }

        return top.value;
    }

    @Override
    public int size() {
        return size;
    }

}
