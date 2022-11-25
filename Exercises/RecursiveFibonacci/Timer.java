package Exercises.RecursiveFibonacci;

public class Timer {
    private long startTime;
    private long endTime;

    public Timer() {
        this.startTime = 0;
        this.endTime = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }
}
