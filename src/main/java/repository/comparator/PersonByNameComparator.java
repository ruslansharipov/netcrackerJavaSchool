package repository.comparator;

import model.Person;

import java.util.Comparator;

public class PersonByNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1 != null && o2 != null) {
            return o1.getName().compareTo(o2.getName());
        } else throw new NullPointerException("Unable to compare Person with null reference");
    }
}
