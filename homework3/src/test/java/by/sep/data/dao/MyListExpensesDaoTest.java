package by.sep.data.dao;

import by.sep.data.ListExpensesTestDataSource;
import by.sep.data.ListExpensesTestSessionFactory;
import by.sep.data.pojo.Expense;
import by.sep.data.pojo.Receiver;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class MyListExpensesDaoTest {
    MyListExpensesDao myDao;
    int testNum = 1;
    int wrongTestNum = 99;
    String testName = "Test Name";
    String testPayDate = "2023-10-30";
    int testReceiver = 1;
    double testValue = 125.58;

    @Before
    public void setUp() throws Exception {
        myDao = MyListExpensesDao.getInstance(ListExpensesTestSessionFactory.getSessionFactory());
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            connection.createStatement()
                    .executeUpdate("truncate table receivers");
            connection.createStatement()
                    .executeUpdate("truncate table expenses");
            connection.createStatement().executeUpdate("INSERT INTO ListExpenses_test.receivers\n" +
                    "(num, name)\n" +
                    "VALUES(" + testNum + ", '" + testName + "');");
            connection.createStatement().executeUpdate("INSERT INTO ListExpenses_test.expenses\n" +
                    "(num, paydate, receiver, value)\n" +
                    "VALUES(" + testNum + ", '" + testPayDate + "', " + testReceiver + ", " + testValue + ");");
        }
    }

    @After
    public void tearDown() throws Exception {
        myDao = null;
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            connection.createStatement()
                    .executeUpdate("truncate table receivers");
            connection.createStatement()
                    .executeUpdate("truncate table expenses");
        }
    }

    @Test
    public void testGetReceiver() {
        Receiver receiver = myDao.getReceiver(testNum);
        Receiver receiver1 = myDao.getReceiver(wrongTestNum);
        assertNotNull(receiver);
        assertEquals(testName, receiver.getName());
        assertEquals(Integer.valueOf(testNum), receiver.getNum());
        assertNull(receiver1);
    }

    @Test
    public void testLoadReceiver() {
        Receiver receiver = myDao.loadReceiver(testNum);
        Receiver receiver1 = myDao.loadReceiver(wrongTestNum);
        assertNotNull(receiver);
        assertEquals(Integer.valueOf(testNum), receiver.getNum());
        assertNotNull(receiver1);
        assertEquals(Integer.valueOf(wrongTestNum), receiver1.getNum());
    }

    @Test
    public void testGetExpense() {
        Expense expense = myDao.getExpense(testNum);
        assertNotNull(expense);
        assertEquals(Integer.valueOf(testNum), expense.getNum());
        assertEquals(testPayDate, expense.getPayDate());
        assertEquals(Integer.valueOf(testReceiver), expense.getReceiver());
        assertEquals(Double.valueOf(testValue), expense.getValue());
    }

    @Test
    public void testAddReceiver() throws SQLException, ClassNotFoundException {
        Receiver receiver = new Receiver(null, "TestName");
        Integer savedId = myDao.addReceiver(receiver);
        assertNotNull(savedId);
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().
                    executeQuery("SELECT COUNT(*) FROM ListExpenses_test.receivers;");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(2, count);
        }
    }

    @Test
    public void testAddExpense() throws SQLException, ClassNotFoundException {
        Expense expense = new Expense(null, "2023-10-30", 1, 125.58);
        Integer savedId = myDao.addExpense(expense);
        assertNotNull(savedId);
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT COUNT(*) FROM ListExpenses_test.expenses;");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(2, count);
        }
    }

    @Test
    public void testUpdateReceiver() throws SQLException, ClassNotFoundException {
        String newTestName = "NewTestName";
        boolean result = myDao.updateReceiver(testNum, newTestName);
        boolean result1 = myDao.updateReceiver(wrongTestNum, newTestName);
        assertTrue(result);
        assertFalse(result1);
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT name FROM ListExpenses_test.receivers " +
                            "WHERE num=" + testNum + ";");
            resultSet.next();
            String name = resultSet.getString(1);
            assertEquals(newTestName, name);
        }
    }

    @Test
    public void testUpdateExpense() throws SQLException, ClassNotFoundException {
        String newTestPayDate = "2023-11-02";
        int newTestReceiver = 1;
        double newTestValue = 103.87;
        boolean result = myDao.updateExpense(testNum, newTestPayDate, newTestReceiver, newTestValue);
        boolean result1 = myDao.updateExpense(wrongTestNum, newTestPayDate, newTestReceiver, newTestValue);
        assertTrue(result);
        assertFalse(result1);
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT paydate,receiver,value " +
                    "FROM ListExpenses_test.expenses WHERE num=" + testNum + ";");
            resultSet.next();
            String payDate = resultSet.getString(1);
            int receiver = resultSet.getInt(2);
            double value = resultSet.getDouble(3);
            assertEquals(newTestPayDate, payDate);
            assertEquals(newTestReceiver, receiver);
            assertEquals(newTestValue, value, 0);
        }
    }

    @Test
    public void testDeleteReceiver() throws SQLException, ClassNotFoundException {
        boolean result = myDao.deleteReceiver(testNum);
        boolean result1 = myDao.deleteReceiver(wrongTestNum);
        assertTrue(result);
        assertFalse(result1);
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT COUNT(*) FROM ListExpenses_test.receivers where NUM=" + testNum + ";");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(0, count);
        }
    }

    @Test
    public void testDeleteExpense() throws SQLException, ClassNotFoundException {
        boolean result = myDao.deleteExpense(testNum);
        boolean result1 = myDao.deleteExpense(wrongTestNum);
        assertTrue(result);
        assertFalse(result1);
        try (Connection connection = ListExpensesTestDataSource.getConnection()) {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT COUNT(*) FROM ListExpenses_test.expenses where NUM=" + testNum + ";");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(0, count);
        }
    }

    @Test //flush method testing
    public void testSaveAfterDeletion() throws SQLException, ClassNotFoundException {
        try (Session session = ListExpensesTestSessionFactory.getSessionFactory().openSession();
             Connection connection = ListExpensesTestDataSource.getConnection()) {
            session.setHibernateFlushMode(FlushMode.COMMIT);
            Transaction transaction = session.beginTransaction();
            Receiver receiver = myDao.getReceiver(testNum);
            session.delete(receiver);
            session.flush();
            session.save(new Receiver(null, testName));
            transaction.commit();

            ResultSet resultSet = connection.createStatement().
                    executeQuery("SELECT COUNT(*) FROM ListExpenses_test.receivers;");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(1, count);
        }
    }

    @Test //refresh method testing
    public void testRefreshAfterUpdate() {
        try (Session session = ListExpensesTestSessionFactory.getSessionFactory().openSession()) {
            Receiver receiver = myDao.getReceiver(testNum);
            receiver.setName("New Test Name");
            session.refresh(receiver);
            assertEquals(testName, receiver.getName());
        }
    }
}