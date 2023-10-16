import java.sql.*;

public class Main {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ListExpenses",
                "user",
                "user");
    }

    public static void main(String[] args) {
        try {
            Statement statement = getConnection().createStatement();
            //Task 4
            statement.executeUpdate("INSERT INTO expenses (paydate,receiver,value) " +
                    "VALUES ('2023-06-25', 5, 15.35)");
            //Task 5
            String template = "INSERT INTO expenses (paydate,receiver,value) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement pStatement = getConnection().prepareStatement(template);
            pStatement.setString(1, "2023-08-08");
            pStatement.setString(2, "7");
            pStatement.setString(3, "28.55");
            pStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery("SELECT paydate, value, name " +
                    "FROM expenses, receivers " +
                    "WHERE receiver=receivers.num");
            while (resultSet.next()) {
                System.out.println(resultSet.getDate("paydate") + " "
                        + resultSet.getDouble("value") + " "
                        + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

