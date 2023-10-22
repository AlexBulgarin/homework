package by.sep.data.Task7;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ListExpensesDao {
    Receiver getReceiver(int num) throws SQLException, ClassNotFoundException;

    ArrayList<Receiver> getReceivers() throws SQLException, ClassNotFoundException;

    Expense getExpense(int num) throws SQLException, ClassNotFoundException;

    ArrayList<Expense> getExpenses() throws SQLException, ClassNotFoundException;

    int addReceiver(Receiver receiver) throws SQLException, ClassNotFoundException;

    int addExpense(Expense expense) throws SQLException, ClassNotFoundException;

}
