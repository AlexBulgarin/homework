package by.sep.dao;

import by.sep.TestDataConfiguration;
import by.sep.pojo.Client;
import by.sep.pojo.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class Homework6Test {
    @Autowired
    Dao dao;
    @Autowired
    DataSource dataSource;
    Connection connection;
    String testId = UUID.randomUUID().toString();
    String testFirstName = "Test First Name";
    String newTestFirstName = "New Test First Name";
    String testLastName = "Test Last Name";
    String newTestLastName = "New Test Last Name";
    String testProductName = "Test Product name";
    String newTestProductName = "New Test Product name";
    LocalDate testProductStartDate = LocalDate.now();
    LocalDate newTestProductStartDate = LocalDate.of(2023, 12, 17);

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO t_client " +
                "(client_id, first_name, last_name) " +
                "VALUES ('" + testId + "', '" + testFirstName + "', '" + testLastName + "');");
        connection.createStatement().executeUpdate("INSERT INTO t_product " +
                "(product_id, product_name, product_start_date) " +
                "VALUES ('" + testId + "', '" + testProductName + "', '" + testProductStartDate + "');");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement()
                .executeUpdate("DELETE FROM t_client");
        connection.createStatement()
                .executeUpdate("DELETE FROM t_product");
        connection.close();
    }

    @Test
    public void testCreate() throws SQLException {
        String id = dao.create(new Client(null, testFirstName, testLastName));
        String id2 = dao.create(new Product(null, testProductName, testProductStartDate));
        assertNotNull(id);
        assertNotNull(id2);

        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_client");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);

        resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_product");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testRead() {
        Client client = (Client) dao.read(Client.class, testId);
        Product product = (Product) dao.read(Product.class, testId);

        assertNotNull(client);
        assertEquals(testId, client.getId());
        assertEquals(testFirstName, client.getFirstName());
        assertEquals(testLastName, client.getLastName());

        assertNotNull(product);
        assertEquals(testId, product.getId());
        assertEquals(testProductName, product.getProductName());
        assertEquals(testProductStartDate, product.getStartDate());
    }

    @Test
    public void testUpdate() {
        Client client = (Client) dao.read(Client.class, testId);
        Product product = (Product) dao.read(Product.class, testId);
        client.setFirstName(newTestFirstName);
        client.setLastName(newTestLastName);
        product.setProductName(newTestProductName);
        product.setStartDate(newTestProductStartDate);
        boolean result = dao.update(client);
        boolean result1 = dao.update(product);

        assertTrue(result);
        assertEquals(newTestFirstName, client.getFirstName());
        assertEquals(newTestLastName, client.getLastName());

        assertTrue(result1);
        assertEquals(newTestProductName, product.getProductName());
        assertEquals(newTestProductStartDate, product.getStartDate());
    }


    @Test
    public void testDelete() throws SQLException {
        Client client = (Client) dao.read(Client.class, testId);
        Product product = (Product) dao.read(Product.class, testId);
        boolean result = dao.delete(client);
        boolean result1 = dao.delete(product);

        assertTrue(result);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_client");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);

        assertTrue(result1);
        resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_product");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }
}