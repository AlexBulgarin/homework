package by.sep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Master {
    @Value("${firstName}")
    private String firstName;
    @Value("${lastName}")
    private String lastName;
    @Autowired
    @Qualifier("studentInfo2")
    private IStudentInfo studentInfo;

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

    public IStudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(IStudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }
}
