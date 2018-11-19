package model;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void testGetAge(){
        LocalDate inputBirthdayDate = LocalDate.fromCalendarFields(new GregorianCalendar(2010, 11, 1));
        Person p = new Person(123,"XXX", inputBirthdayDate);
        Integer expectedAge = 8;
        Integer age = p.getAge();
        assertEquals(expectedAge, age);
    }
}
