package model;

import org.joda.time.LocalDate;

public class Person {

    /**
     * Person's id
     */
    private Integer id;
    /**
     * Person's name
     */
    private String name;
    /**
     * Person's date of birth
     */
    private LocalDate birthDate;

    /**
     * Constructs Person object
     *
     * @param id        {@link #id}
     * @param name      {@link #name}
     * @param birthDate {@link #birthDate}
     */
    public Person(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    /**
     * @return person's id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Calculates the age of the person
     *
     * @return person's age in years
     */
    public Integer getAge() {
        return LocalDate
                .now()
                .minusYears(getBirthDay().getYear())
                .getYear();
    }

    /**
     * @return the person's birthday
     */
    public LocalDate getBirthDay() {
        return birthDate;
    }

    /**
     * Checks the equality of this object with specified object
     *
     * @param obj object to check equality with
     * @return <tt>true</tt> if objects are equal, else return false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Person) {
            Person person = (Person) obj;
            return person.getName().equals(this.name)
                    && person.getId().equals(this.id)
                    && person.getBirthDay().equals(this.birthDate);
        } else return false;
    }

    @Override
    public String toString() {
        return "{[id=" + id.toString() + "][name=" + name + "][age=" + getAge().toString() + "]}";
    }
}
