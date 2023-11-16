package by.sep.data;

import by.sep.data.dao.MyListExpensesDao;
import by.sep.data.pojo.Expense;
import by.sep.data.pojo.Receiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final MyListExpensesDao DAO = MyListExpensesDao.getInstance(ListExpensesSessionFactory.getSessionFactory());
    private static final String ENTER_RECEIVER_ID = "Enter receiver ID";
    private static final String ENTER_EXPENSE_ID = "Enter expense ID";
    private static final String NO_RECEIVER = "There's no receiver with such ID in database";
    private static final String NO_EXPENSE = "There's no expense with such ID in database";

    public static void main(String[] args) {
        try {
            getInfo();
            getMenu();
        } catch (Exception e) {
            System.err.println("Program failed with exception: " + e.getMessage());
            System.exit(9);
        }
        System.exit(0);
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
                        getReceiver(scanner);
                        break;
                    case "getExpense":
                        getExpense(scanner);
                        break;
                    case "saveReceiver":
                        saveReceiver(scanner);
                        break;
                    case "saveExpense":
                        saveExpense(scanner);
                        break;
                    case "updateReceiver":
                        updateReceiver(scanner);
                        break;
                    case "updateExpense":
                        updateExpense(scanner);
                        break;
                    case "deleteReceiver":
                        deleteReceiver(scanner);
                        break;
                    case "deleteExpense":
                        deleteExpense(scanner);
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Unknown command");
                        getInfo();
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid argument value");
            System.exit(3);
        }
    }

    private static void checkDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
        } catch (ParseException e) {
            System.out.println("Invalid date input");
            System.exit(4);
        }
    }

    private static void getReceiver(Scanner scanner) {
        System.out.println(ENTER_RECEIVER_ID);
        Receiver receiverToGet = DAO.getReceiver(scanner.nextInt());
        if (receiverToGet == null) {
            System.out.println(NO_RECEIVER);
            System.exit(1);
        }
        System.out.println(receiverToGet);
    }

    private static void getExpense(Scanner scanner) {
        System.out.println(ENTER_EXPENSE_ID);
        Expense expenseToGet = DAO.getExpense(scanner.nextInt());
        if (expenseToGet == null) {
            System.out.println(NO_EXPENSE);
            System.exit(2);
        }
        System.out.println(expenseToGet);
    }

    private static void saveReceiver(Scanner scanner) {
        System.out.println("Enter receiver name");
        System.out.println("Added receiver with id:" +
                DAO.addReceiver(new Receiver(null, scanner.next())));
    }

    private static void saveExpense(Scanner scanner) {
        System.out.println("Enter expense date of payment");
        String date = scanner.next();
        checkDate(date);
        System.out.println(ENTER_RECEIVER_ID);
        int receiverId = scanner.nextInt();
        if (DAO.getReceiver(receiverId) == null) {
            System.out.println(NO_RECEIVER);
            System.exit(1);
        }
        System.out.println("Enter expense value");
        double value = scanner.nextDouble();
        System.out.println("Added expense with id:" +
                DAO.addExpense(new Expense(null, date, receiverId, value)));
    }

    private static void updateReceiver(Scanner scanner) {
        System.out.println(ENTER_RECEIVER_ID);
        int receiverIdToUpdate = scanner.nextInt();
        System.out.println("Enter receiver new name");
        String newName = scanner.next();
        boolean updateReceiverResult = DAO.updateReceiver(receiverIdToUpdate, newName);
        if (!updateReceiverResult) {
            System.out.println(NO_RECEIVER);
            System.exit(1);
        }
        System.out.println("Receiver was successfully updated");
    }

    private static void updateExpense(Scanner scanner) {
        System.out.println(ENTER_EXPENSE_ID);
        int expenseIdToUpdate = scanner.nextInt();
        System.out.println("Enter expense date of payment");
        String newDate = scanner.next();
        checkDate(newDate);
        System.out.println(ENTER_RECEIVER_ID);
        int newReceiverId = scanner.nextInt();
        if (DAO.getReceiver(newReceiverId) == null) {
            System.out.println(NO_RECEIVER);
            System.exit(1);
        }
        System.out.println("Enter expense value");
        double newValue = scanner.nextDouble();
        boolean updateExpenseResult = DAO.updateExpense(expenseIdToUpdate, newDate, newReceiverId, newValue);
        if (!updateExpenseResult) {
            System.out.println(NO_EXPENSE);
            System.exit(2);
        }
        System.out.println("Expense was successfully updated");
    }

    private static void deleteReceiver(Scanner scanner) {
        System.out.println(ENTER_RECEIVER_ID);
        boolean receiverDeletionResult = DAO.deleteReceiver(scanner.nextInt());
        if (!receiverDeletionResult) {
            System.out.println(NO_RECEIVER);
            System.exit(1);
        }
        System.out.println("Receiver was successfully deleted");
    }

    private static void deleteExpense(Scanner scanner) {
        System.out.println(ENTER_EXPENSE_ID);
        boolean expenseDeletionResult = DAO.deleteExpense(scanner.nextInt());
        if (!expenseDeletionResult) {
            System.out.println(NO_EXPENSE);
            System.exit(2);
        }
        System.out.println("Expense was successfully deleted");
    }
}