package Exercises.RecursiveFibonacci;

public class Fibonacci {
    private Integer[] array;

    public Fibonacci(Integer[] array) {
        this.array = array;
    }

    public int fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public Integer fibdp(int n) {
        if (n <= 1) {
            array[n] = n;
        } else if (array[n] != 0) {
            array[n] = fibdp(n - 1) + fibdp(n - 2);
        }
        return array[n];
    }

    public Integer iterative(int n) {
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }
}
