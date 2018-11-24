package repository.checker;

import model.Person;

public class FIOChecker implements Checker<Person> {
    /**
     * Query matching to which will be checked in check() method
     */
    private String query;

    /**
     * Constructs FIOChecker object
     *
     * @param s {@link #query}
     */
    public FIOChecker(String s) {
        this.query = s;
    }

    /**
     * Checks if the persons name contains {@link #query}
     * @param person person to check
     * @return <tt>true</tt> if person's name contains {@link #query}
     */
    @Override
    public boolean check(Person person) {
        if (person != null && query != null) {
            return person.getName().contains(query);
        }
        return false;
    }
}
