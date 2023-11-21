package by.sep.data.pojos.employee;

import java.util.Date;

public class EmployeeDetails {
    private Double salary;
    private Date employmentDate;
    private Date dateOfBirth;

    public EmployeeDetails() {
    }

    public EmployeeDetails(Double salary, Date employmentDate, Date dateOfBirth) {
        this.salary = salary;
        this.employmentDate = employmentDate;
        this.dateOfBirth = dateOfBirth;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
