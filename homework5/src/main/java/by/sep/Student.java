package by.sep;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class Student implements InitializingBean, DisposableBean {
    private String firstName;
    private String lastName;
    private Map<String, String> marks;
    @Autowired
    private StudentInfo studentInfo;

    public static Student getInstance() {
        return new Student();
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Student initialization");
    }

    @Override
    public void destroy() {
        System.out.println("Student destruction");
    }

    public void defaultInit() {
        System.out.println("Default student initialization");
    }

    public void defaultDestroy() {
        System.out.println("Default student destruction");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, String> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, String> marks) {
        this.marks = marks;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }
}
