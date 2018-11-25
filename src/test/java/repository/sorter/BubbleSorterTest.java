package repository.sorter;

import model.Person;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryTest;
import repository.comparator.PersonByNameComparator;
import repository.storage.Repository;

import static org.junit.Assert.*;

public class BubbleSorterTest {

    private Object[] dataStorage;
    private BubbleSorter<Person> bubbleSorter = new BubbleSorter<>();
    private PersonByNameComparator byName = new PersonByNameComparator();
    private Repository<Person> notEmptyRepository;

    @Before
    public void setUp() {
        notEmptyRepository = RepositoryTest.getNotEmptyRepository();
        dataStorage = new Object[notEmptyRepository.size()];
        for (int i = 0; i < notEmptyRepository.size(); i++) {
            dataStorage[i] = notEmptyRepository.get(i);
        }
    }

    @Test
    public void sort() {
        bubbleSorter.sort(byName, dataStorage, notEmptyRepository.size());
        Person actual = (Person) dataStorage[1];
        Person expected = RepositoryTest.HETFIELD;

        assertEquals(expected, actual);
    }
}