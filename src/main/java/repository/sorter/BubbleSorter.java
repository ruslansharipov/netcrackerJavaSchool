package repository.sorter;

import java.util.Comparator;

public class BubbleSorter<E> implements Sorter<E> {
    @Override
    public void sort(Comparator<E> comparator, int size, Object[] dataStorage) {
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
