package by.sep.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task4 {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        String payDate = args[1];
        int receiver = Integer.parseInt(args[2]);
        double value = Double.parseDouble(args[3]);
        addAndPrint(num, payDate, receiver, value);
    }

    public static void addAndPrint(int num, String payDate, int receiver, double value) {
        try {
            Statement statement = ListExpensesDataSource.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO expenses " +
                    "VALUES (" + num + ", '" + payDate + "', " + receiver + ", " + value + ")");
            ResultSet resultSet = statement.executeQuery("SELECT paydate, value, name " +
                    "FROM expenses, receivers " +
                    "WHERE receiver=receivers.num");
            while (resultSet.next()) {
                System.out.println(resultSet.getDate("paydate") + " "
                        + resultSet.getDouble("value") + " "
                        + resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
