package by.sep.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task5 {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        String payDate = args[1];
        int receiver = Integer.parseInt(args[2]);
        double value = Double.parseDouble(args[3]);
        addAndPrint(num, payDate, receiver, value);
    }

    public static void addAndPrint(int num, String payDate, int receiver, double value) {
        try {
            PreparedStatement preparedStatement = ListExpensesDataSource.getConnection().
                    prepareStatement("INSERT INTO expenses VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, num);
            preparedStatement.setString(2, payDate);
            preparedStatement.setInt(3, receiver);
            preparedStatement.setDouble(4, value);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery("SELECT paydate, value, name " +
                    "FROM expenses, receivers " +
                    "WHERE receiver=receivers.num");
            while (resultSet.next()) {
                System.out.println(resultSet.getDate("paydate") + " "
                        + resultSet.getDouble("value") + " "
                        + resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
