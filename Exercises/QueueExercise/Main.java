package Exercises.QueueExercise;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<String> q = new ArrayDeque<String>();
        q.add("Hello");
        q.add("World");
        q.add("!");
        q.add("This");
        q.add("is");
        q.add("a");
        q.add("test");

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }

        System.out.println("\n\n");

        q = new PriorityQueue<String>((a, b) -> a.compareTo(b));
        q.add("Hello");
        q.add("World");
        q.add("!");
        q.add("This");
        q.add("is");
        q.add("a");
        q.add("test");

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }
}
