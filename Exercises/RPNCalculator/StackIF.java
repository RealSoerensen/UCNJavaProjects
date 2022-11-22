package Exercises.RPNCalculator;

public interface StackIF<T> {
    public void push(T t);

    public T pop();

    public T peek();

    public int size();
}
