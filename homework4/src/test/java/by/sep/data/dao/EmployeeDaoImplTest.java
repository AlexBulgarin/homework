package by.sep.data.dao;

import by.sep.data.pojos.employee.Driver;
import by.sep.data.pojos.employee.Employee;
import by.sep.data.pojos.employee.EmployeeDetails;
import by.sep.data.pojos.employee.Manager;
import by.sep.data.util.Hw4TestDataSource;
import by.sep.data.util.Hw4TestSessionFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class EmployeeDaoImplTest {
    static EmployeeDao dao;
    static Connection connection;
    long testEmployeeId = 1;
    long testManagerId = 2;
    long testDriverId = 3;
    long wrongId = 4;
    String testFirstName = "Test First Name";
    String testLastName = "Test Last Name";
    double testSalary = 1000;
    Date testEmploymentDate = Date.valueOf("2020-10-10");
    Date testDateOfBirth = Date.valueOf("2000-11-11");
    String testLaptopSerial = "Test Laptop Serial";
    long testDriverLicenseId = 123;
    String testCarModel = "Test Car Model";
    Employee testEmployee = new Employee(null, testFirstName, testLastName,
            new EmployeeDetails(testSalary, testEmploymentDate, testDateOfBirth));
    Manager testManager = new Manager(null, testFirstName, testLastName,
            new EmployeeDetails(testSalary, testEmploymentDate, testDateOfBirth),
            testLaptopSerial);
    Driver testDriver = new Driver(null, testFirstName, testLastName,
            new EmployeeDetails(testSalary, testEmploymentDate, testDateOfBirth),
            testDriverLicenseId, testCarModel);

    @BeforeClass
    public static void beforeClass() throws Exception {
        dao = new EmployeeDaoImpl(Hw4TestSessionFactory.getSessionFactory());
        connection = Hw4TestDataSource.getConnection();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        dao = null;
        connection.close();
    }

    @Before
    public void setUp() throws Exception {
        connection.createStatement().executeUpdate("INSERT INTO T_EMPLOYEE VALUES(" +
                testEmployeeId + ",'" +
                testDateOfBirth + "','" +
                testEmploymentDate + "'," +
                testSalary + ",'" +
                testFirstName + "','" +
                testLastName + "');");
        connection.createStatement().executeUpdate("INSERT INTO T_MANAGER VALUES(" +
                testManagerId + ",'" +
                testDateOfBirth + "','" +
                testEmploymentDate + "'," +
                testSalary + ",'" +
                testFirstName + "','" +
                testLastName + "','" +
                testLaptopSerial + "');");
        connection.createStatement().executeUpdate("INSERT INTO T_DRIVER VALUES(" +
                testDriverId + ",'" +
                testDateOfBirth + "','" +
                testEmploymentDate + "'," +
                testSalary + ",'" +
                testFirstName + "','" +
                testLastName + "','" +
                testCarModel + "'," +
                testDriverLicenseId + ");");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement().executeUpdate("DELETE FROM T_EMPLOYEE;");
        connection.createStatement().executeUpdate("DELETE FROM T_MANAGER;");
        connection.createStatement().executeUpdate("DELETE FROM T_DRIVER;");
    }

    @Test
    public void testCreate() throws SQLException {
        Long id1 = dao.create(testEmployee);
        Long id2 = dao.create(testManager);
        Long id3 = dao.create(testDriver);

        assertNotNull(id1);
        assertNotNull(id2);
        assertNotNull(id3);

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM T_EMPLOYEE;");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);

        resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM T_MANAGER;");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);

        resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM T_DRIVER;");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testRead() {
        Employee employee = dao.read(testEmployeeId);
        Manager manager = (Manager) dao.read(testManagerId);
        Driver driver = (Driver) dao.read(testDriverId);
        Employee nullEmployee = dao.read(wrongId);

        assertEquals(testEmployeeId, employee.getEmployeeId().longValue());
        assertEquals(testFirstName, employee.getEmployeeFirstName());
        assertEquals(testLastName, employee.getEmployeeLastName());
        assertEquals(testSalary, employee.getEmployeeDetails().getSalary(), 0);
        assertEquals(testEmploymentDate, employee.getEmployeeDetails().getEmploymentDate());
        assertEquals(testDateOfBirth, employee.getEmployeeDetails().getDateOfBirth());

        assertEquals(testManagerId, manager.getEmployeeId().longValue());
        assertEquals(testFirstName, manager.getEmployeeFirstName());
        assertEquals(testLastName, manager.getEmployeeLastName());
        assertEquals(testSalary, manager.getEmployeeDetails().getSalary(), 0);
        assertEquals(testEmploymentDate, manager.getEmployeeDetails().getEmploymentDate());
        assertEquals(testDateOfBirth, manager.getEmployeeDetails().getDateOfBirth());
        assertEquals(testLaptopSerial, manager.getLaptopSerial());

        assertEquals(testDriverId, driver.getEmployeeId().longValue());
        assertEquals(testFirstName, driver.getEmployeeFirstName());
        assertEquals(testLastName, driver.getEmployeeLastName());
        assertEquals(testSalary, driver.getEmployeeDetails().getSalary(), 0);
        assertEquals(testEmploymentDate, driver.getEmployeeDetails().getEmploymentDate());
        assertEquals(testDateOfBirth, driver.getEmployeeDetails().getDateOfBirth());
        assertEquals(testCarModel, driver.getCarModel());
        assertEquals(testDriverLicenseId, driver.getDriverLicenseId().longValue());

        assertNull(nullEmployee);
    }

    @Test
    public void testUpdate() throws SQLException {
        String newTestFirstName = "New Test First Name";
        String newTestLastName = "New Test Last Name";
        double newTestSalary = 2000;
        Date newTestEmploymentDate = Date.valueOf("2021-12-12");
        Date newTestDateOfBirth = Date.valueOf("1999-05-06");

        boolean result = dao.update(testEmployeeId, newTestFirstName, newTestLastName,
                new EmployeeDetails(newTestSalary, newTestEmploymentDate, newTestDateOfBirth));
        boolean falseResult = dao.update(wrongId, newTestFirstName, newTestLastName,
                new EmployeeDetails(newTestSalary, newTestEmploymentDate, newTestDateOfBirth));

        assertTrue(result);
        assertFalse(falseResult);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM T_EMPLOYEE;");
        resultSet.next();
        assertEquals(newTestFirstName, resultSet.getString("employeeFirstName"));
        assertEquals(newTestLastName, resultSet.getString("employeeLastName"));
        assertEquals(newTestSalary, resultSet.getDouble("salary"), 0);
        assertEquals(newTestEmploymentDate, resultSet.getDate("employmentDate"));
        assertEquals(newTestDateOfBirth, resultSet.getDate("dateOfBirth"));
    }

    @Test
    public void testDelete() throws SQLException {
        boolean result1 = dao.delete(testEmployeeId);
        boolean result2 = dao.delete(testManagerId);
        boolean result3 = dao.delete(testDriverId);
        boolean falseResult1 = dao.delete(wrongId);

        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertFalse(falseResult1);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM T_EMPLOYEE;");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);

        resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM T_MANAGER;");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);

        resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM T_DRIVER;");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }
}