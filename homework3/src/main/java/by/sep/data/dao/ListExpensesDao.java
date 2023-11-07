package by.sep.data.dao;

import by.sep.data.pojo.Expense;
import by.sep.data.pojo.Receiver;

public interface ListExpensesDao {
    Receiver getReceiver(int num);

    Receiver loadReceiver(int num);

    Expense getExpense(int num);

    Integer addReceiver(Receiver receiver);

    Integer addExpense(Expense expense);

    boolean updateReceiver(int num, String newName);

    boolean updateExpense(int num, String newPayDate, int newReceiver, double newValue);

    void refreshReceiverFromDataBase(Receiver receiver);

    void refreshExpenseFromDataBase(Expense expense);

    boolean deleteReceiver(int num);

    boolean deleteExpense(int num);

}
