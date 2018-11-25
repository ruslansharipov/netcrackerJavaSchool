package repository.sorter;

import repository.storage.Repository;

import java.util.Comparator;

public class BubbleSorter<E> implements Sorter<E> {
    /**
     * Sorts specified dataStorage using in order thar comparator sets
     *
     * @param comparator sets the way of comparing objects
     * @param dataStorage dataStorage to sort
     */
    @Override
    public void sort(Comparator<E> comparator, Object[] dataStorage, int size) {
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                E currentElement = (E) dataStorage[j];
                E previousElement = (E) dataStorage[j - 1];
                if (comparator.compare(previousElement, currentElement) > 0) {
                    dataStorage[j] = previousElement;
                    dataStorage[j - 1] = currentElement;
                }
            }
        }
    }
}
