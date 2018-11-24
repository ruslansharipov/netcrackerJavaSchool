package repository.storage;

import repository.checker.Checker;
import repository.sorter.BubbleSorter;
import repository.sorter.Sorter;

import java.util.Comparator;

public class Repository<E> implements Storage<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] dataStorage;
    private int size;
    private Sorter<E> sorter;

    public Repository() {
        sorter = new BubbleSorter<>();
        initializeDataStorage();
    }

    private void initializeDataStorage() {
        size = 0;
        dataStorage = new Object[DEFAULT_CAPACITY];
    }

    public void setSorter(Sorter<E> sorter) {
        this.sorter = sorter;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        if (size + 1 > dataStorage.length) {
            extend();
        }
        dataStorage[size++] = e;
        return true;
    }

    private void extend() {
        int oldCapacity = dataStorage.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newStorage = new Object[newCapacity];
        for (int i = 0; i <= size; i++) {
            newStorage[i] = dataStorage[i];
        }
        dataStorage = newStorage;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) dataStorage[index];
    }

    private void rangeCheck(int index) {
        if (index > size || size == 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
    }

    @Override
    public E remove(int index) {
        E objectToRemove = get(index);
        fastRemove(index);
        return objectToRemove;
    }

    private void fastRemove(int index) {
        for (int i = index + 1; i < size; i++) {
            dataStorage[i - 1] = dataStorage[i];
        }
        size--;
    }

    @Override
    public boolean remove(E e) {
        if (e == null) {
            for (int index = 0; index < size; index++) {
                if (dataStorage[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (e.equals(dataStorage[index])) {
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean clear() {
        initializeDataStorage();
        return true;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = this.get(index);
        dataStorage[index] = element;
        return oldValue;
    }

    @Override
    public void sortBy(Comparator<E> comparator) {
        sorter.sort(comparator, size, dataStorage);
    }

    @Override
    public Storage<E> search(Checker<E> checker) {
        Storage<E> searchList = new Repository<>();
        for (int i = 0; i < size; i++) {
            E elementToCheck = get(i);
            if (checker.check(elementToCheck)) {
                searchList.add(elementToCheck);
            }
        }
        return searchList;
    }

    @Override
    public boolean contains(E e) {
        if (e != null) {
            for (int i = 0; i < size; i++) {
                if (e.equals(dataStorage[i])){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(dataStorage[i].toString());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}