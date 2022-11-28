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

    /**
     * If the array is empty or has one element, return. Otherwise, partition the
     * array, sort the left
     * partition, and sort the right partition
     * 
     * @param start the start index of the array
     * @param end   the last index of the array
     */
    private void quicksort(int start, int end) {
        if (end - start < 2) {
            return;
        }

        int pivotIndex = partition(start, end);
        quicksort(start, pivotIndex - 1);
        quicksort(pivotIndex + 1, end);
    }

    /**
     * The partition function takes the last element as the pivot, places the pivot
     * element at its correct
     * position in sorted array, and places all smaller (smaller than pivot) to left
     * of pivot and all
     * greater elements to right of pivot
     * 
     * @param start Starting index, where the sorting begins
     * @param end   the last index of the array
     * @return The index of the pivot element.
     */
    private int partition(int start, int end) {
        // This is using the last element as the pivot
        int pivot = array[end];
        int i = start - 1;

        // Sorting the array.
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

        // swap array[i+1] and array[end] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }
}
