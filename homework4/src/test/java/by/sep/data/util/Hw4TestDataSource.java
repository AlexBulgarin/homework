package by.sep.data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Hw4TestDataSource {
    private static Hw4TestDataSource dataSource;

    private Hw4TestDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getListExpensesTestConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hw4_test",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new Hw4TestDataSource();
        }
        return dataSource.getListExpensesTestConnection();
    }
}
