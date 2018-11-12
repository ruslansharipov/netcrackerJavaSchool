package labs.repository;

import java.util.Arrays;

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
        dataStorage = Arrays.copyOf(dataStorage, newCapacity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return (E) dataStorage[index];
    }

    @Override
    public E remove(int index) {
        E objectToRemove = get(index);
        fastRemove(index);
        size--;
        return objectToRemove;
    }

    private void fastRemove(int index){
        System.arraycopy(dataStorage, index + 1, dataStorage, index, size - index - 1);
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
