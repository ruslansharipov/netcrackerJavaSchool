package repository;

import java.util.Comparator;
import java.util.List;

public interface Storage<E>{
    int size();
    boolean isEmpty();
    boolean add(E e);
    E get(int index);
    E remove(int index);
    boolean remove(E e);
    boolean clear();
    void bubbleSort(Comparator<E> comparator);
    Storage<E> search(Checker<E> checker);
}
