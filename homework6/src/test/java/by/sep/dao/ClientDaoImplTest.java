package by.sep.dao;

import by.sep.TestDataConfiguration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class ClientDaoImplTest {
    @Autowired
    ClientDaoImpl clientDao;
    @Autowired
    static DataSource dataSource;
    static Connection connection;
    String testId = UUID.randomUUID().toString();

    @BeforeClass
    public static void beforeClass() throws Exception {
        connection = dataSource.getConnection();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        connection.close();
    }

    @Before
    public void setUp() throws Exception {
        connection.createStatement().executeUpdate("INSERT INTO t_client " +
                "(client_id, first_name, last_name) " +
                "VALUES ('" + testId + "', 'Test First Name', 'Test Last Name');");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create() {
    }

    @Test
    public void read() {
    }

    @Test
    public void deletePersonById() {
    }
}