package Exercises.RecursiveFibonacci;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        Timer timer = new Timer();

        Fibonacci fibonacci = new Fibonacci(array);
        timer.start();
        System.out.println(fibonacci.fib(46));
        timer.stop();
        System.out.println(timer.getElapsedTime());

        timer.start();
        System.out.println(fibonacci.fibdp(46));
        timer.stop();
        System.out.println(timer.getElapsedTime());

        timer.start();
        System.out.println(fibonacci.iterative(46));
        timer.stop();
        System.out.println(timer.getElapsedTime());
    }
}
