package repository.comparator;

import model.Person;

import java.util.Comparator;

public class PersonByNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1 != null && o2 != null) {
            Character p1 = o1.getName().charAt(0);
            Character p2 = o2.getName().charAt(0);
            return p1.compareTo(p2);
        } else throw new NullPointerException("Unable to compare Person with null reference");
    }
}
