package by.sep.task2;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.AbstractMap;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void testTask2() {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("task2ContextConfiguration.xml")) {
            Student student = (Student) context.getBean("student");
            assertNotNull(student);
            assertEquals("Peter", student.getFirstName());
            assertEquals("Parker", student.getLastName());
            assertEquals(3, student.getMarks().size());
            assertTrue(student.getMarks().entrySet().contains(new AbstractMap.SimpleEntry<>("Math", "A")));
            assertTrue(student.getMarks().entrySet().contains(new AbstractMap.SimpleEntry<>("Physics", "A")));
            assertTrue(student.getMarks().entrySet().contains(new AbstractMap.SimpleEntry<>("Biology", "B")));
        }
    }
}