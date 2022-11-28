package Exercises.SortingExercise;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[50];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println("Unsorted array:");
        printArray(array);

        Quicksort quicksort = new Quicksort(array);
        quicksort.sort();

        System.out.println("Sorted array:");
        printArray(quicksort.getArray());
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
