package Exercises.InterfaceImplentation;

public class OrderedPair<T> implements Pairable<T> {
    private T first;
    private T second;

    public OrderedPair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public void changeOrder() {
        T temp = first;
        first = second;
        second = temp;
    }
}