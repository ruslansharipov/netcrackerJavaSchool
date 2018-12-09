package injector;

import model.Person;
import org.junit.Before;
import org.junit.Test;
import repository.storage.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class InjectorTest {

    private Repository<Person> repository;
    private Properties properties;

    @Before
    public void setUp() {
        repository = new Repository<>();
        properties = new Properties();
    }

    @Test
    public void injectSorter() throws IllegalAccessException, IOException {
        String expected = null;
        try (InputStream input = new FileInputStream(Injector.PATH_TO_PROPERTIES)) {
            properties.load(input);
            expected = properties.getProperty("Sorter");
        }
        Injector.injectSorter(repository);
        String actual = repository.getSorter().getClass().getName();
        assertEquals(expected, actual);
    }
}