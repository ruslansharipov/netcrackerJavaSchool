package repository;

import repository.checker.Checker;

import java.util.Comparator;

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
    boolean contains(E e);
}
