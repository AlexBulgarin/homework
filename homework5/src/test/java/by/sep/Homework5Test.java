package by.sep;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.AbstractMap;

import static org.junit.Assert.*;

public class Homework5Test {
    static ClassPathXmlApplicationContext context;
    static Student student;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        student = (Student) context.getBean("student");
    }

    @AfterClass
    public static void afterClass() {
        context.close();
    }

    @Test
    public void testTask2() {
        assertNotNull(student);
        assertEquals("Peter", student.getFirstName());
        assertEquals("Parker", student.getLastName());
        assertEquals(3, student.getMarks().size());
        assertTrue(student.getMarks().entrySet().contains(new AbstractMap.SimpleEntry<>("Math", "A")));
        assertTrue(student.getMarks().entrySet().contains(new AbstractMap.SimpleEntry<>("Physics", "A")));
        assertTrue(student.getMarks().entrySet().contains(new AbstractMap.SimpleEntry<>("Biology", "B")));
    }


    @Test
    public void testTask8() {
        assertEquals("S00123", student.getStudentInfo().getStudentCardNumber());
        assertEquals("R00435", student.getStudentInfo().getRecordBookNumber());
        assertEquals("L00342", student.getStudentInfo().getLibraryCardNumber());
    }
}