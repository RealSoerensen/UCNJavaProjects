package Exercises.SortingExercise;

public class Main {
    public static void main(String[] args) {
        int[] array = { 20, 35, -15, 7, 55, 1, -22 };
        Quicksort quicksort = new Quicksort(array);
        quicksort.sort();
        for (int i = 0; i < quicksort.getArray().length; i++) {
            System.out.println(array[i]);
        }
    }
}
