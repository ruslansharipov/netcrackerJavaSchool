package labs.model;

import org.joda.time.LocalDate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Person {
    private int id;
    private String name;
    private LocalDate birthDate;
    private Gender gender;

    public Person(int id, String name, LocalDate birthDate, Gender gender) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        throw new NotImplementedException();
    }

    public LocalDate getBirthDay() {
        return birthDate;
    }

    public Gender getGender(){
        return gender;
    }

    @Override
    public String toString() {
        return "{name=" + name + "}";
    }
}
