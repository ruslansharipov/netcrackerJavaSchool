package repository.comparator;

import model.Person;

import java.util.Comparator;

public class PersonByIdComparator implements Comparator<Person> {
    /**
     * Compares tho Person objects by their id
     *
     * @param o1 first object
     * @param o2 second object
     * @return positive int value if the id of the first person is more of that second person, 0 if the ids are equal,
     * else returns negative int value
     * @throws NullPointerException if one or two of specified objects are null
     */
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getId()
                .compareTo(o2.getId());
    }
}
