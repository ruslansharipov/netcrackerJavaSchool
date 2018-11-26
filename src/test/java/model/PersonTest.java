package model;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    public static final Integer TEST_PERSONS_ID = 1;
    public static final String TEST_PERSONS_NAME = "TEST_PERSON";
    public static final LocalDate TEST_PERSONS_BIRTH_DATE =
            LocalDate.fromCalendarFields(new GregorianCalendar(2010, 11, 1));
    public static Person TEST_PERSON = new Person(TEST_PERSONS_ID, TEST_PERSONS_NAME, TEST_PERSONS_BIRTH_DATE);

    @Test
    public void testGetAge(){
        Integer expectedAge = 8;
        Integer age = TEST_PERSON.getAge();
        assertEquals(expectedAge, age);
    }

    @Test
    public void getId() {
        Integer actualId = TEST_PERSON.getId();
        assertEquals(TEST_PERSONS_ID, actualId);
    }

    @Test
    public void getName() {
        String actualString = TEST_PERSON.getName();
        assertEquals(TEST_PERSONS_NAME, actualString);
    }

    @Test
    public void getAge() {
        Integer actualAge = TEST_PERSON.getAge();
        Integer expectedAge = LocalDate.now()
                .minusYears(TEST_PERSONS_BIRTH_DATE.getYear())
                .getYear();
        assertEquals(expectedAge, actualAge);
    }

    @Test
    public void getBirthDay() {
        LocalDate actual = TEST_PERSON.getBirthDay();
        assertEquals(TEST_PERSONS_BIRTH_DATE, actual);
    }

    @Test
    public void equals() {
        Person anotherPerson = new Person(TEST_PERSONS_ID, TEST_PERSONS_NAME, TEST_PERSONS_BIRTH_DATE);
        boolean actualEquals = anotherPerson.equals(TEST_PERSON);
        assertTrue(actualEquals);
    }

    @Test
    public void toStringTest() {
        String actualString = TEST_PERSON.toString();
        String expectedString = "{[id=" + TEST_PERSONS_ID.toString() + "][name=" + TEST_PERSONS_NAME + "][age=" + TEST_PERSON.getAge() + "]}";
        assertEquals(expectedString, actualString);
    }
}
