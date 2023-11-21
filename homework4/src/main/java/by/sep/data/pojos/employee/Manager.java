package by.sep.data.pojos.employee;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_MANAGER")
public class Manager extends Employee {
    private String laptopSerial;

    public Manager() {
    }

    public Manager(Long employeeId, String employeeFirstName, String employeeLastName, EmployeeDetails employeeDetails, String laptopSerial) {
        super(employeeId, employeeFirstName, employeeLastName, employeeDetails);
        this.laptopSerial = laptopSerial;
    }

    public String getLaptopSerial() {
        return laptopSerial;
    }

    public void setLaptopSerial(String laptopSerial) {
        this.laptopSerial = laptopSerial;
    }
}
