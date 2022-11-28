package Exercises.SortingExercise;

public class Quicksort {
    int[] array;

    public Quicksort(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void sort() {
        quicksort(0, array.length - 1);
    }

    public void quicksort(int start, int end) {
        if (end - start < 2) {
            return;
        }

        int pivotIndex = partition(start, end);
        quicksort(start, pivotIndex - 1);
        quicksort(pivotIndex + 1, end);
    }

    private int partition(int start, int end) {
        // This is using the first element as the pivot
        int pivot = array[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (array[j] <= pivot) {
                i++;

                // swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // swap array[i+1] and array[high] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }
}
