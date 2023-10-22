package by.sep.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ListExpensesDataSource {
    private static ListExpensesDataSource dataSource;

    private ListExpensesDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getListExpensesConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ListExpenses",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new ListExpensesDataSource();
        }
        return dataSource.getListExpensesConnection();
    }
}
