package model;

import org.joda.time.LocalDate;

public class Person {
    private int id;
    private String name;
    private LocalDate birthDate;

    public Person(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return LocalDate
                .now()
                .minusYears(getBirthDay().getYear())
                .getYear();
    }

    public LocalDate getBirthDay() {
        return birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Person) {
            Person person = (Person) obj;
            return person.getName().equals(this.name)
                    && person.getId() == this.id
                    && person.getBirthDay().equals(this.birthDate);
        } else return false;
    }

    @Override
    public String toString() {
        return "{name=" + name + "}";
    }
}
