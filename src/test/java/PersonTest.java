import labs.model.Person;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

public class PersonTest {

    @Test
    public void test(){
        LocalDate inputBirthdayDate = LocalDate.fromCalendarFields(new GregorianCalendar(2010, 11, 1));
        Person p = new Person(123,"XXX", inputBirthdayDate);
        Integer expectedAge = 7;
        Integer age = p.getAge();
        assertEquals(expectedAge, age);

    }
}
