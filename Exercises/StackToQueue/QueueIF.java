package Exercises.StackToQueue;

public interface QueueIF<T> {
    void enqueue(T t);

    T dequeue();

    T getFront();

    boolean isEmpty();

    void clear();
}
