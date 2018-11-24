package repository.sorter;

import model.Person;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryTest;
import repository.comparator.PersonByNameComparator;
import repository.storage.Repository;

import static org.junit.Assert.*;

public class BubbleSorterTest {

    private BubbleSorter<Person> bubbleSorter = new BubbleSorter<>();
    private PersonByNameComparator byName = new PersonByNameComparator();
    private Repository<Person> notEmptyRepository;

    @Before
    public void setUp() {
        notEmptyRepository = RepositoryTest.getNotEmptyRepository();
    }

    @Test
    public void sort() {
        bubbleSorter.sort(byName, notEmptyRepository);
        Person actual = notEmptyRepository.get(1);
        Person expected = RepositoryTest.HETFIELD;

        assertEquals(expected, actual);
    }
}