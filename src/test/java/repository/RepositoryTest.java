package repository;

import injector.Injector;
import model.Person;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.checker.FIOChecker;
import repository.comparator.PersonByAgeComparator;
import repository.comparator.PersonByIdComparator;
import repository.comparator.PersonByNameComparator;
import repository.sorter.BubbleSorter;
import repository.sorter.Sorter;
import repository.storage.Repository;

import static org.junit.Assert.*;

public class RepositoryTest {
    public static Person HETFIELD = new Person(1, "Hetfield", LocalDate.parse("1973-01-02"));
    private Repository<Person> notEmptyRepository;
    private Repository<Person> emptyRepository = new Repository<>();

    public static Repository<Person> getNotEmptyRepository() {
        Repository<Person> repository = new Repository<>();
        repository.add(new Person(1, "Hetfield", LocalDate.parse("1973-01-02")));
        repository.add(new Person(2, "Ulrich", LocalDate.parse("1975-02-03")));
        repository.add(new Person(3, "Hammett", LocalDate.parse("1971-04-05")));
        repository.add(new Person(4, "Jason", LocalDate.parse("1972-12-23")));
        repository.add(new Person(5, "Newsted", LocalDate.parse("1973-09-15")));
        return repository;
    }

    @Before
    public void setUp() {
        notEmptyRepository = getNotEmptyRepository();
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
        Person trujillo = new Person(6, "Trujillo", LocalDate.parse("1980-04-20"));
        boolean actualAdd = notEmptyRepository.add(trujillo);
        boolean actualContains = notEmptyRepository.contains(trujillo);
        int actualSize = notEmptyRepository.size();
        int expectedSize = 6;

        assertTrue(actualAdd);
        assertTrue(actualContains);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void get() {
        Person actual = notEmptyRepository.get(0);
        Person expected = HETFIELD;
        assertEquals(expected, actual);
    }

    @Test
    public void removeByIndex() {
        Person actualPerson = notEmptyRepository.remove(0);
        Person expectedPerson = new Person(1, "Hetfield", LocalDate.parse("1973-01-02"));
        int actualSize = notEmptyRepository.size();
        int expectedSize = 4;

        assertEquals(expectedPerson, actualPerson);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeByObject() {
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

        notEmptyRepository.add(null);
        actualOnNull = notEmptyRepository.remove(null);

        assertTrue(actualOnNull);
    }

    @Test
    public void bubbleSort() throws IllegalAccessException {
        Injector.injectSorter(notEmptyRepository);
        notEmptyRepository.sortBy(new PersonByNameComparator());
        Person actualPerson = notEmptyRepository.get(1);
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

    @Test
    public void setSorter() {
        Sorter<Person> expectedSorter = new BubbleSorter<>();
        notEmptyRepository.setSorter(expectedSorter);
        Sorter<Person> actualSorter = notEmptyRepository.getSorter();

        assertEquals(expectedSorter, actualSorter);
    }

    @Test
    public void sortBy() throws IllegalAccessException {
        Injector.injectSorter(notEmptyRepository);
        notEmptyRepository.sortBy(new PersonByNameComparator());
        Person actual = notEmptyRepository.get(1);
        Person expected = HETFIELD;

        assertEquals(expected, actual);

        notEmptyRepository.sortBy(new PersonByAgeComparator());
        actual = notEmptyRepository.get(1);

        assertEquals(expected, actual);

        notEmptyRepository.sortBy(new PersonByIdComparator());
        actual = notEmptyRepository.get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void contains() {
        boolean actual = notEmptyRepository.contains(HETFIELD);
        boolean expected = true;

        assertEquals(expected, actual);

        actual = emptyRepository.contains(HETFIELD);
        expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void set() {
        notEmptyRepository.set(3, HETFIELD);
        Person expected = HETFIELD;
        Person actual = notEmptyRepository.get(3);

        assertEquals(expected, actual);
    }

    @Test
    public void extend() {
        for (int i = 0; i < 10; i++) {
            notEmptyRepository.add(HETFIELD);
        }
        int actualSize = notEmptyRepository.size();
        int expectedSize = 15;

        assertEquals(expectedSize, actualSize);
    }
}