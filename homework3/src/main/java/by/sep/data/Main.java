package by.sep.data;

import by.sep.data.dao.MyListExpensesDao;
import by.sep.data.pojo.Expense;
import by.sep.data.pojo.Receiver;

import java.util.Scanner;

public class Main {
    private static void init() {
        getInfo();
    }

    private static void getInfo() {
        System.out.println("Command list:");
        System.out.println("info\ngetReceiver\ngetExpense\nsaveReceiver\nsaveExpense\nupdateReceiver" +
                "\nupdateExpense\ndeleteReceiver\ndeleteExpense\nexit");
    }

    public static void main(String[] args) {
        MyListExpensesDao dao = MyListExpensesDao.getInstance(ListExpensesSessionFactory.getSessionFactory());
        try (Scanner scanner = new Scanner(System.in)) {
            init();
            while (true) {
                String action = scanner.next();
                switch (action) {
                    case "info":
                        getInfo();
                        break;
                    case "getReceiver":
                        System.out.println("Enter receiver ID");
                        System.out.println(dao.getReceiver(scanner.nextInt()));
                        break;
                    case "getExpense":
                        System.out.println("Enter expense ID");
                        System.out.println(dao.getExpense(scanner.nextInt()));
                        break;
                    case "saveReceiver":
                        System.out.println("Enter receiver name");
                        System.out.println("Added id:" + dao.addReceiver(new Receiver(null, scanner.next())));
                        break;
                    case "saveExpense":
                        System.out.println("Enter expense paydate");
                        String date = scanner.next();
                        System.out.println("Enter receiver ID");
                        int receiver = scanner.nextInt();
                        System.out.println("Enter expense value");
                        double value = scanner.nextDouble();
                        System.out.println("Added id:" + dao.addExpense(new Expense(null, date, receiver, value)));
                        break;
                    case "updateReceiver":
                        System.out.println("Enter receiver ID");
                        int receiverNum = scanner.nextInt();
                        System.out.println("Enter receiver new name");
                        String newName = scanner.next();
                        System.out.println(dao.updateReceiver(receiverNum, newName));
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
                        System.out.println(dao.updateExpense(expenseNum, newDate, newReceiver, newValue));
                        break;
                    case "deleteReceiver":
                        System.out.println("Enter receiver ID");
                        System.out.println(dao.deleteReceiver(scanner.nextInt()));
                        break;
                    case "deleteExpense":
                        System.out.println("Enter expense ID");
                        System.out.println(dao.deleteExpense(scanner.nextInt()));
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
