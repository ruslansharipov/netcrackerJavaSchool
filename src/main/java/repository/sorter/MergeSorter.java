package repository.sorter;

import java.util.Comparator;

public class MergeSorter<E> implements Sorter<E> {
    /**
     * dataStorage of repository that needs to be sorted
     */
    private Object[] dataStorage;
    /**
     * Help array that stores pre-sorted parts needed to be merged in correct order in dataStorage
     */
    private Object[] buffer;
    /**
     * Comparator that used to compare objects in {@link #mergeParts(int, int, int)}
     */
    private Comparator<E> comparator;

    @Override
    public void sort(Comparator<E> comparator, Object[] dataStorage, int size) {
        this.dataStorage = dataStorage;
        int length = dataStorage.length;
        this.buffer = new Object[length];
        mergeSort(0, length - 1);
    }

    /**
     * Recursive method that divides dataStorage into smaller parts and then merges them in correct order
     *
     * @param lowerIndex  lower index of divided part of dataStorage
     * @param higherIndex higher index of divided part of dataStorage
     */
    private void mergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            mergeSort(lowerIndex, middle);
            mergeSort(middle + 1, higherIndex);
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    /**
     * Merges parts of {@link #dataStorage} divided in {@link #mergeSort(int, int)} in correct order
     *
     * @param lowerIndex
     * @param middle
     * @param higherIndex
     */
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            buffer[i] = dataStorage[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (comparator.compare((E) buffer[i], (E) buffer[j]) <= 0) {
                dataStorage[k] = buffer[i];
                i++;
            } else {
                dataStorage[k] = buffer[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            dataStorage[k] = buffer[i];
            k++;
            i++;
        }
    }
}
