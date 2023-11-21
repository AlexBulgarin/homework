package by.sep.data.util;

import by.sep.data.dao.EmployeeDao;
import by.sep.data.dao.EmployeeDaoImpl;
import by.sep.data.pojos.employee.Employee;
import by.sep.data.pojos.employee.EmployeeDetails;
import by.sep.data.pojos.employee.Manager;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDaoImpl(Hw4SessionFactory.getSessionFactory());
        Employee manager = new Manager(null, "Alex", "Bulgarin", new EmployeeDetails(
                2345.56, Date.valueOf("2010-09-28"), Date.valueOf("1987-09-13")),
                "S12345678");
        dao.create(manager);
        dao.create(manager);
        dao.create(manager);
    }
}
