package repository;

import model.Person;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.checker.FIOChecker;
import repository.comparator.PersonByNameComparator;

import static org.junit.Assert.*;

public class RepositoryTest {
    private static Person HETFIELD = new Person(1, "Hetfield", LocalDate.parse("1973-01-02"));
    private Repository<Person> notEmptyRepository;
    private Repository<Person> emptyRepository = new Repository<>();

    @Before
    public void setUp() {
        notEmptyRepository = new Repository<>();
        notEmptyRepository.add(new Person(1, "Hetfield", LocalDate.parse("1973-01-02")));
        notEmptyRepository.add(new Person(2, "Ulrich", LocalDate.parse("1975-02-03")));
        notEmptyRepository.add(new Person(3, "Hammett", LocalDate.parse("1971-04-05")));
        notEmptyRepository.add(new Person(4, "Jason", LocalDate.parse("1972-12-23")));
        notEmptyRepository.add(new Person(5, "Newsted", LocalDate.parse("1973-09-15")));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void size() {
        int actual = notEmptyRepository.size();
        int expected = 5;
        assertEquals(expected, actual);

        actual = emptyRepository.size();
        expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void isEmpty() {
        boolean actual = notEmptyRepository.isEmpty();
        assertFalse(actual);

        actual = emptyRepository.isEmpty();
        assertTrue(actual);
    }

    @Test
    public void add() {
        boolean actualBoolean = notEmptyRepository.add(new Person(6, "Trujillo", LocalDate.parse("1980-04-20")));
        int actualSize = notEmptyRepository.size();
        int expectedSize = 6;

        assertTrue(actualBoolean);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void get() {
        Person actual = notEmptyRepository.get(0);
        Person expected = HETFIELD;
        assertEquals(expected, actual);
    }

    @Test
    public void remove() {
        Person actualPerson = notEmptyRepository.remove(0);
        Person expectedPerson = new Person(1, "Hetfield", LocalDate.parse("1973-01-02"));
        int actualSize = notEmptyRepository.size();
        int expectedSize = 4;

        assertEquals(expectedPerson, actualPerson);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void remove1() {
        boolean actualOnNull = notEmptyRepository.remove(null);
        boolean actualOnNotExisting = notEmptyRepository.remove(new Person(2, "Hetfield", LocalDate.parse("1973-01-02")));
        boolean actualOnExisting = notEmptyRepository.remove(new Person(1, "Hetfield", LocalDate.parse("1973-01-02")));

        assertFalse(actualOnNull);
        assertFalse(actualOnNotExisting);
        assertTrue(actualOnExisting);

        actualOnNull = emptyRepository.remove(null);
        actualOnNotExisting = emptyRepository.remove(new Person(1, "Hetfield", LocalDate.parse("1973-01-02")));

        assertFalse(actualOnNull);
        assertFalse(actualOnNotExisting);
    }

    @Test
    public void bubbleSort() {
        notEmptyRepository.sortBy(new PersonByNameComparator());

        Person actualPerson = notEmptyRepository.get(0);
        Person expectedPerson = HETFIELD;

        assertEquals(expectedPerson, actualPerson);

    }

    @Test
    public void clear() {
        notEmptyRepository.clear();

        int actualSize = notEmptyRepository.size();
        int expectedSize = 0;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void search() {
        FIOChecker hetfieldSearch = new FIOChecker("Hetfield");

        int expectedSize = 1;
        int actualSize = notEmptyRepository
                .search(hetfieldSearch)
                .size();

        assertEquals(expectedSize, actualSize);

        expectedSize = 0;
        actualSize = emptyRepository
                .search(hetfieldSearch)
                .size();

        assertEquals(expectedSize, actualSize);
    }
}