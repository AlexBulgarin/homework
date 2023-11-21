package by.sep.data.pojos.employee;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_DRIVER")
public class Driver extends Employee {
    private Long driverLicenseId;
    private String carModel;

    public Driver() {
    }

    public Driver(Long employeeId, String employeeFirstName, String employeeLastName, EmployeeDetails employeeDetails, Long driverLicenseId, String carModel) {
        super(employeeId, employeeFirstName, employeeLastName, employeeDetails);
        this.driverLicenseId = driverLicenseId;
        this.carModel = carModel;
    }

    public Long getDriverLicenseId() {
        return driverLicenseId;
    }

    public void setDriverLicenseId(Long driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
