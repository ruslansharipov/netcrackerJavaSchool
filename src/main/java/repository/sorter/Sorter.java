package repository.sorter;

import repository.storage.Repository;

import java.util.Comparator;

public interface Sorter <E>{
    void sort(Comparator<E> comparator, Object[] dataStorage, int size);
}
