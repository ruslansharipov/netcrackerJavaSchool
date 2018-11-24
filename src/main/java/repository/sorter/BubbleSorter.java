package repository.sorter;

import repository.storage.Repository;

import java.util.Comparator;

public class BubbleSorter<E> implements Sorter<E> {
    @Override
    public void sort(Comparator<E> comparator, Repository<E> repository) {
        int size = repository.size();
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                E currentElement = repository.get(j);
                E previousElement = repository.get(j - 1);
                if (comparator.compare(previousElement, currentElement) > 0) {
                    repository.set(j, previousElement);
                    repository.set(j - 1, currentElement);
                }
            }
        }
    }
}
