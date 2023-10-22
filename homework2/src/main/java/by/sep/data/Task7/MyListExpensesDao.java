package by.sep.data.Task7;

import by.sep.data.ListExpensesDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MyListExpensesDao implements ListExpensesDao {
    private static MyListExpensesDao instance;

    private MyListExpensesDao() {
    }

    public static MyListExpensesDao getInstance() {
        if (instance == null) {
            instance = new MyListExpensesDao();
        }
        return instance;
    }

    @Override
    public Receiver getReceiver(int num) throws SQLException, ClassNotFoundException {
        Statement statement = ListExpensesDataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM receivers " +
                "WHERE num=" + num);
        resultSet.next();
        Receiver receiver = new Receiver();
        receiver.setNum(num);
        receiver.setName(resultSet.getString("name"));
        resultSet.close();
        statement.close();
        return receiver;
    }

    @Override
    public ArrayList<Receiver> getReceivers() throws SQLException, ClassNotFoundException {
        Statement statement = ListExpensesDataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM receivers");
        ArrayList<Receiver> receiversList = new ArrayList<>();
        while (resultSet.next()) {
            Receiver receiver = new Receiver();
            receiver.setNum(resultSet.getInt("num"));
            receiver.setName(resultSet.getString("name"));
            receiversList.add(receiver);
        }
        resultSet.close();
        statement.close();
        return receiversList;
    }

    @Override
    public Expense getExpense(int num) throws SQLException, ClassNotFoundException {
        Statement statement = ListExpensesDataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT paydate, receiver, value " +
                "FROM expenses " +
                "WHERE num=" + num);
        resultSet.next();
        Expense expense = new Expense();
        expense.setNum(num);
        expense.setPaydate(resultSet.getString("paydate"));
        expense.setReceiver(resultSet.getInt("receiver"));
        expense.setValue(resultSet.getDouble("value"));
        resultSet.close();
        statement.close();
        return expense;
    }

    @Override
    public ArrayList<Expense> getExpenses() throws SQLException, ClassNotFoundException {
        Statement statement = ListExpensesDataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM expenses");
        ArrayList<Expense> expensesList = new ArrayList<>();
        while (resultSet.next()) {
            Expense expense = new Expense();
            expense.setNum(resultSet.getInt("num"));
            expense.setPaydate(resultSet.getString("paydate"));
            expense.setReceiver(resultSet.getInt("receiver"));
            expense.setValue(resultSet.getDouble("value"));
            expensesList.add(expense);
        }
        resultSet.close();
        statement.close();
        return expensesList;
    }

    @Override
    public int addReceiver(Receiver receiver) throws SQLException, ClassNotFoundException {
        Statement statement = ListExpensesDataSource.getConnection().createStatement();
        int i = statement.executeUpdate("INSERT INTO receivers VALUES (" + receiver.getNum() +
                ", '" + receiver.getName() + "')");
        statement.close();
        return i;
    }

    @Override
    public int addExpense(Expense expense) throws SQLException, ClassNotFoundException {
        Statement statement = ListExpensesDataSource.getConnection().createStatement();
        int i = statement.executeUpdate("INSERT INTO expenses VALUES (" + expense.getNum() +
                ", '" + expense.getPaydate() + "', " + expense.getReceiver() + ", " +
                expense.getValue() + ")");
        statement.close();
        return i;
    }
}
