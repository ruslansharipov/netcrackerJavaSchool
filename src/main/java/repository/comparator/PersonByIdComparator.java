package repository.comparator;

import model.Person;

import java.util.Comparator;

public class PersonByIdComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1 != null && o2 != null) {
            return o1.getId()
                    .compareTo(o2.getId());
        } else throw new NullPointerException("Unable to compare Person with null reference");
    }
}
