package by.sep.data;

import by.sep.data.dao.MyListExpensesDao;
import by.sep.data.pojo.Expense;
import by.sep.data.pojo.Receiver;

import java.util.Scanner;

public class Main {
    private static final MyListExpensesDao DAO = MyListExpensesDao.getInstance(ListExpensesSessionFactory.getSessionFactory());

    public static void main(String[] args) {
        init();
        getMenu();
    }


    private static void init() {
        getInfo();
    }

    private static void getInfo() {
        System.out.println("Command list:");
        System.out.println("info\ngetReceiver\ngetExpense\nsaveReceiver\nsaveExpense\nupdateReceiver" +
                "\nupdateExpense\ndeleteReceiver\ndeleteExpense\nexit");
    }

    private static void getMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String action = scanner.next();
                switch (action) {
                    case "info":
                        getInfo();
                        break;
                    case "getReceiver":
                        System.out.println("Enter receiver ID");
                        System.out.println(DAO.getReceiver(scanner.nextInt()));
                        break;
                    case "getExpense":
                        System.out.println("Enter expense ID");
                        System.out.println(DAO.getExpense(scanner.nextInt()));
                        break;
                    case "saveReceiver":
                        System.out.println("Enter receiver name");
                        System.out.println("Added id:" + DAO.addReceiver(new Receiver(null, scanner.next())));
                        break;
                    case "saveExpense":
                        System.out.println("Enter expense paydate");
                        String date = scanner.next();
                        System.out.println("Enter receiver ID");
                        int receiver = scanner.nextInt();
                        System.out.println("Enter expense value");
                        double value = scanner.nextDouble();
                        System.out.println("Added id:" + DAO.addExpense(new Expense(null, date, receiver, value)));
                        break;
                    case "updateReceiver":
                        System.out.println("Enter receiver ID");
                        int receiverNum = scanner.nextInt();
                        System.out.println("Enter receiver new name");
                        String newName = scanner.next();
                        System.out.println(DAO.updateReceiver(receiverNum, newName));
                        break;
                    case "updateExpense":
                        System.out.println("Enter expense ID");
                        int expenseNum = scanner.nextInt();
                        System.out.println("Enter expense paydate");
                        String newDate = scanner.next();
                        System.out.println("Enter receiver ID");
                        int newReceiver = scanner.nextInt();
                        System.out.println("Enter expense value");
                        double newValue = scanner.nextDouble();
                        System.out.println(DAO.updateExpense(expenseNum, newDate, newReceiver, newValue));
                        break;
                    case "deleteReceiver":
                        System.out.println("Enter receiver ID");
                        System.out.println(DAO.deleteReceiver(scanner.nextInt()));
                        break;
                    case "deleteExpense":
                        System.out.println("Enter expense ID");
                        System.out.println(DAO.deleteExpense(scanner.nextInt()));
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Unknown command");
                        getInfo();
                        break;
                }
            }
        }
    }
}
