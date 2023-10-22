package by.sep.data.Task7;

import java.sql.SQLException;

public class DaoTest {
    public static void main(String[] args) {
        try {
            ListExpensesDao dao = MyListExpensesDao.getInstance();
            System.out.println(dao.getReceiver(4));
            System.out.println(dao.getExpense(2));
            System.out.println(dao.getReceivers());
            System.out.println(dao.getExpenses());
            Receiver r = new Receiver();
            r.setNum(9);
            r.setName("Страховая компания \"Белгосстрах\"");
            dao.addReceiver(r);
            Expense e = new Expense();
            e.setNum(13);
            e.setPaydate("2023-05-25");
            e.setReceiver(9);
            e.setValue(49.57);
            dao.addExpense(e);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}