package repository.comparator;

import model.Person;

import java.util.Comparator;

public class PersonByNameComparator implements Comparator<Person> {
    /**
     * Compares tho Person objects by their id
     *
     * @param o1 first object
     * @param o2 second object
     * @return positive int value if the name of the first person has to be closer to the beginning of the repository
     * that name of the second person, 0 if the names are equal, else returns negative int value
     * @throws NullPointerException if one or two of specified objects are null
     */
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName()
                .compareTo(o2.getName());
    }
}
