package repository;

import java.util.Comparator;

public class Repository<E> implements Storage<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] dataStorage;
    private int size;

    public Repository() {
        initializeDataStorage();
    }

    private void initializeDataStorage() {
        size = 0;
        dataStorage = new Object[DEFAULT_CAPACITY];
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
        if (index > size || size == 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return (E) dataStorage[index];
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
    public void bubbleSort(Comparator<E> comparator) {
        for (int i = 1; i < this.size(); i++) {
            for (int j = 1; j < this.size(); j++) {
                E currentElement = this.get(j);
                E previousElement = this.get(j - 1);
                if (comparator.compare(previousElement, currentElement) > 0) {
                    this.dataStorage[j] = previousElement;
                    this.dataStorage[j - 1] = currentElement;
                }
            }
        }
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