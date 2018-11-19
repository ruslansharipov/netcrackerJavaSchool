package repository;

import com.sun.istack.internal.NotNull;
import model.Person;
import org.joda.time.LocalDate;

public class FIOChecker implements Checker<Person> {
    private String query;

    public FIOChecker(String s) {
        this.query = s;
    }

    @Override
    public boolean check(Person person) {
        if (person != null && query != null) {
            return person.getName().contains(query);
        }
        return false;
    }
}
