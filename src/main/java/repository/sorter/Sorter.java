package repository.sorter;

import java.util.Comparator;

public interface Sorter <E>{
    void sort(Comparator<E> comparator);
}
