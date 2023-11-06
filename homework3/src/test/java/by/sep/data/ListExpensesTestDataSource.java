package by.sep.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ListExpensesTestDataSource {
    private static ListExpensesTestDataSource dataSource;

    private ListExpensesTestDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getListExpensesTestConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ListExpenses_test",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new ListExpensesTestDataSource();
        }
        return dataSource.getListExpensesTestConnection();
    }
}
