package repository.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.checker.Checker;
import repository.sorter.Sorter;

import java.util.Comparator;

public class Repository<E> implements Storage<E> {
    /**
     * Static logger for logging events
     */
    private static Logger logger = LogManager.getLogger(Repository.class);
    /**
     * Default initial capacity
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The array which stores the elements of the Repository
     */
    private Object[] dataStorage;

    /**
     * The size of the Repository (The number of elements it contains)
     */
    private int size;

    /**
     * The Sorter which will sort the dataStorage using its implementation
     */
    @injector.Sorter
    private Sorter<E> sorter;

    /**
     * Constructs an empty repository with the initial capacity of 10
     */
    public Repository() {
        initializeDataStorage();
        logger.trace("Repository initialized");
    }

    /**
     * Initializes new repository or clears already existing data by reassigning initial values to it
     */
    private void initializeDataStorage() {
        size = 0;
        dataStorage = new Object[DEFAULT_CAPACITY];
    }


    /**
     * @return sorter if was set.
     */
    public Sorter<E> getSorter() {
        return sorter;
    }

    /**
     * Sets Sorter of the Repository
     *
     * @param sorter - sorter which implements Sorter interface
     */
    public void setSorter(Sorter<E> sorter) {
        this.sorter = sorter;
    }

    /**
     * @return the number of elements in repository
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return <tt>true</tt> if the repository contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds element to the end of the repository
     * @param e - element to add
     * @return <tt>true</tt> if element successfully added
     */
    @Override
    public boolean add(E e) {
        if (size + 1 > dataStorage.length) {
            extend();
        }
        dataStorage[size++] = e;
        logger.trace("element added");
        return true;
    }

    /**
     * Extends capacity of the dataStorage array by creating a new array with extended capacity.
     * Copies elements from old dataStorage to new one
     */
    private void extend() {
        int oldCapacity = dataStorage.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newStorage = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newStorage[i] = dataStorage[i];
        }
        dataStorage = newStorage;
        logger.trace("DataStorage extended. oldCapacity = " + oldCapacity + " newCapacity = " + newCapacity);
    }

    /**
     * @param index index of the element to return
     * @return the element in the specified position
     * @throws IndexOutOfBoundsException
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) dataStorage[index];
    }

    /**
     * Checks if the given index is in the range. If now throws appropriate runtime exception
     * @param index index to check
     */
    private void rangeCheck(int index) {
        if (index > size || size == 0) {
            logger.error("Repository.rangeCheck() (index > size || size == 0) == true",
                    new IndexOutOfBoundsException(String.valueOf(index)));
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
    }

    /**
     * Removes element from the repository in the specified position
     * @param index index of the element to remove
     * @return removed element
     */
    @Override
    public E remove(int index) {
        E objectToRemove = get(index);
        fastRemove(index);
        return objectToRemove;
    }

    /**
     * Private remove method.
     * Skips range checking and doesn't return any value
     * @param index index of element to remove
     */
    private void fastRemove(int index) {
        logger.trace("Object removed", get(index));
        for (int i = index + 1; i < size; i++) {
            dataStorage[i - 1] = dataStorage[i];
        }
        dataStorage[--size] = null;
    }

    /**
     * Removes first occurrence of element if exist
     * @param e element to remove
     * @return <tt>true</tt> if the element exist in the repository
     */
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

    /**
     * Clears all elements from repository by reassigning initial values to it
     * @return <tt>true</tt>
     */
    @Override
    public boolean clear() {
        initializeDataStorage();
        logger.trace("repository cleared");
        return true;
    }

    /**
     *Replaces the element at the specified index position with the specified element
     * @param index position of the element to replace
     * @param element the element to be stored instead of replaced
     * @return the replaced element
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = this.get(index);
        dataStorage[index] = element;
        logger.trace("oldValue = " + oldValue.toString(), " newValue = " + element.toString());
        return oldValue;
    }

    /**
     * Sorts repository using comparator
     * @param comparator comparator that implements the way of comparing two objects
     */
    @Override
    public void sortBy(Comparator<E> comparator) {
        sorter.sort(comparator, dataStorage, size);
        logger.trace("repository sorted");
    }

    /**
     * Searches for the elements that will satisfy the checker
     * @param checker object containing conditions of searching
     * @return the Storage object with elements that satisfy conditions of checker
     */
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

    /**
     * Searches for the occurrence of the specified object
     * @param e element to find
     * @return <tt>true</tt> if the element exists in the repository
     */
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