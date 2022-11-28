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
        quicksort(0, array.length);
    }

    public void quicksort(int start, int end) {
        if (end - start < 2) {
            return;
        }

        int pivotIndex = partition(start, end);
        quicksort(start, pivotIndex);
        quicksort(pivotIndex + 1, end);
    }

    private int partition(int start, int end) {
        // This is using the first element as the pivot
        int pivot = array[start];
        int i = start;
        int j = end;

        while (i < j) {
            // NOTE: empty loop body
            while (i < j && array[--j] >= pivot)
                ;
            if (i < j) {
                array[i] = array[j];
            }

            // NOTE: empty loop body
            while (i < j && array[++i] <= pivot)
                ;
            if (i < j) {
                array[j] = array[i];
            }
        }

        array[j] = pivot;
        return j;
    }

}
