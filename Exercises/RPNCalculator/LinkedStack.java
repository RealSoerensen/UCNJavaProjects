package Exercises.RPNCalculator;

public class LinkedStack<T> implements StackIF<T> {
    private int size;
    private Node<T> top;

    @SuppressWarnings("hiding")
    private class Node<T> {
        private T value;
        private LinkedStack<T>.Node<T> next;

        public Node(T value, LinkedStack<T>.Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public LinkedStack<T>.Node<T> getNext() {
            return next;
        }
    }

    public LinkedStack() {
        size = 0;
        top = null;
    }

    @Override
    public void push(T t) {
        Node<T> newNode = new Node<T>(t, top);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (top == null) {
            return null;
        }

        T value = top.getValue();
        top = top.getNext();
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (top == null) {
            return null;
        }

        return top.getValue();
    }

    @Override
    public int size() {
        return size;
    }

}
