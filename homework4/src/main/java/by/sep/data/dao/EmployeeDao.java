package by.sep.data.dao;

import by.sep.data.pojos.employee.Employee;
import by.sep.data.pojos.employee.EmployeeDetails;

public interface EmployeeDao {
    Long create(Employee employee);

    Employee read(long id);

    boolean update(long id, String newFirstName, String newLastName, EmployeeDetails newEmployeeDetails);

    boolean delete(long id);
}
