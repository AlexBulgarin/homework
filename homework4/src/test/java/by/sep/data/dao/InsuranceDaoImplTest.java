package by.sep.data.dao;

import by.sep.data.pojos.insurance.AutoInsurance;
import by.sep.data.pojos.insurance.InsuranceInfo;
import by.sep.data.pojos.insurance.TravelInsurance;
import by.sep.data.util.Hw4TestDataSource;
import by.sep.data.util.Hw4TestSessionFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.*;

public class InsuranceDaoImplTest {
    static InsuranceDao dao;
    static Connection connection;
    String testId1 = UUID.randomUUID().toString();
    String testId2 = UUID.randomUUID().toString();
    String testInsurantName = "Test Name";
    double testPrice = 99.99;
    int testDuration = 12;
    String testVehicleModel = "Test Vehicle Model";
    String testVehicleNumber = "Test Number";
    String testCountryOfVisit = "Test Country";
    String testVisaNumber = "Test Visa Number";
    AutoInsurance testAutoInsurance = new AutoInsurance(null, testInsurantName,
            new InsuranceInfo(testPrice, testDuration), testVehicleModel, testVehicleNumber);
    TravelInsurance testTravelInsurance = new TravelInsurance(null, testInsurantName,
            new InsuranceInfo(testPrice, testDuration), testCountryOfVisit, testVisaNumber);

    @BeforeClass
    public static void beforeClass() throws Exception {
        dao = new InsuranceDaoImpl(Hw4TestSessionFactory.getSessionFactory());
        connection = Hw4TestDataSource.getConnection();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        dao = null;
        connection.close();
    }

    @Before
    public void setUp() throws Exception {
        connection.createStatement().executeUpdate("INSERT INTO T_INSURANCE VALUES('" +
                testId1 + "'," +
                testDuration + "," +
                testPrice + ",'" +
                testInsurantName + "');");
        connection.createStatement().executeUpdate("INSERT INTO T_AUTO_INSURANCE VALUES('" +
                testVehicleModel + "','" +
                testVehicleNumber + "','" +
                testId1 + "');");
        connection.createStatement().executeUpdate("INSERT INTO T_INSURANCE VALUES('" +
                testId2 + "'," +
                testDuration + "," +
                testPrice + ",'" +
                testInsurantName + "');");
        connection.createStatement().executeUpdate("INSERT INTO T_TRAVEL_INSURANCE VALUES('" +
                testCountryOfVisit + "','" +
                testVisaNumber + "','" +
                testId2 + "');");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement()
                .executeUpdate("DELETE FROM T_AUTO_INSURANCE");
        connection.createStatement()
                .executeUpdate("DELETE FROM T_TRAVEL_INSURANCE");
        connection.createStatement()
                .executeUpdate("DELETE FROM T_INSURANCE");
    }

    @Test
    public void testCreate() throws SQLException {
        String id1 = dao.create(testAutoInsurance);
        String id2 = dao.create(testTravelInsurance);

        assertNotNull(id1);
        assertNotNull(id2);

        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM T_INSURANCE;");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(4, actualCount);

        resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM T_AUTO_INSURANCE;");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);

        resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM T_TRAVEL_INSURANCE;");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);

    }

    @Test
    public void testRead() {
        AutoInsurance autoInsurance = (AutoInsurance) dao.read(testId1);
        TravelInsurance travelInsurance = (TravelInsurance) dao.read(testId2);

        assertEquals(testId1, autoInsurance.getInsuranceId());
        assertEquals(testInsurantName, autoInsurance.getInsurantName());
        assertEquals(testPrice, autoInsurance.getInsuranceInfo().getInsurancePrice(), 0);
        assertEquals(testDuration, autoInsurance.getInsuranceInfo().getInsuranceDuration().intValue());
        assertEquals(testVehicleModel, autoInsurance.getVehicleModel());
        assertEquals(testVehicleNumber, autoInsurance.getVehicleNumber());

        assertEquals(testId2, travelInsurance.getInsuranceId());
        assertEquals(testInsurantName, travelInsurance.getInsurantName());
        assertEquals(testPrice, travelInsurance.getInsuranceInfo().getInsurancePrice(), 0);
        assertEquals(testDuration, travelInsurance.getInsuranceInfo().getInsuranceDuration().intValue());
        assertEquals(testCountryOfVisit, travelInsurance.getCountryOfVisit());
        assertEquals(testVisaNumber, travelInsurance.getVisaNumber());
    }

    @Test
    public void testUpdate() throws SQLException {
        String newTestInsurantName = "New Test Name";
        double newTestPrice = 150.99;
        int newTestDuration = 24;

        boolean result = dao.update(testId2, newTestInsurantName, new InsuranceInfo(newTestPrice, newTestDuration));

        assertTrue(result);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM T_INSURANCE " +
                "WHERE insuranceId='" + testId2 + "';");
        resultSet.next();
        assertEquals(newTestInsurantName, resultSet.getString("insurantName"));
        assertEquals(newTestPrice, resultSet.getDouble("insurancePrice"), 0);
        assertEquals(newTestDuration, resultSet.getInt("insuranceDuration"));
    }

    @Test
    public void testDelete() throws SQLException {
        boolean result = dao.delete(testId1);

        assertTrue(result);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT COUNT(*) FROM T_INSURANCE;");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(1, actualCount);
    }
}