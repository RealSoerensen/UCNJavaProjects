package Exercises.StackToQueue;

public class LinkedStack<T> implements QueueIF<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    @SuppressWarnings("hiding")
    private class Node<T> {
        private T value;
        private LinkedStack<T>.Node<T> next;

        private Node(T value, LinkedStack<T>.Node<T> next) {
            this.value = value;
            this.next = next;
        }

        private T getValue() {
            return value;
        }

        private LinkedStack<T>.Node<T> getNext() {
            return next;
        }

        private void setNext(LinkedStack<T>.Node<T> next) {
            this.next = next;
        }
    }

    public LinkedStack() {
        last = null;
        first = null;
        size = 0;
    }

    /**
     * If the queue is empty, the new node is both the first and last node.
     * Otherwise, the new node is the
     * last node and the previous last node's next node is the new node
     * 
     * @param t The data to be stored in the node.
     */
    @Override
    public void enqueue(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry, last);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    /**
     * If the queue is empty, return null. Otherwise, return the value of the first
     * node and set the first
     * node to the next node
     * 
     * @return The value of the first node in the queue.
     */
    @Override
    public T dequeue() {
        if (first == null) {
            return null;
        }

        T value = first.getValue();
        first = first.getNext();
        size--;
        return value;
    }

    @Override
    public T getFront() {
        if (last == null) {
            return null;
        }

        return first.getValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        last = null;
        first = null;
        size = 0;
    }
}
