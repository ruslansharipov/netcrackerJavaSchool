package labs.repository;

public interface Storage<E>{
    int size();
    boolean isEmpty();
    boolean add(E e);
    E get(int index);
    E remove(int index);
    boolean remove(E e);
    boolean clear();
}
