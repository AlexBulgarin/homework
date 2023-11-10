package by.sep.data.dao;

import by.sep.data.pojo.Expense;
import by.sep.data.pojo.Receiver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MyListExpensesDao implements ListExpensesDao {
    private static MyListExpensesDao instance;
    private final SessionFactory sessionFactory;

    private MyListExpensesDao(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    public static MyListExpensesDao getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new MyListExpensesDao(sessionFactory);
        }
        return instance;
    }

    @Override
    public Receiver getReceiver(int num) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Receiver.class, num);
        }

    }

    @Override
    public Receiver loadReceiver(int num) {
        try (Session session = sessionFactory.openSession()) {
            return session.load(Receiver.class, num);
        }
    }

    @Override
    public Expense getExpense(int num) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Expense.class, num);
        }
    }

    @Override
    public Integer addReceiver(Receiver receiver) {
        Transaction transaction = null;
        Integer savedId;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            savedId = (Integer) session.save(receiver);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
        return savedId;
    }

    @Override
    public Integer addExpense(Expense expense) {
        Transaction transaction = null;
        Integer savedId;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            savedId = (Integer) session.save(expense);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
        return savedId;
    }

    @Override
    public boolean updateReceiver(int num, String newName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Receiver receiver = session.get(Receiver.class, num);
            if (receiver == null) return false;
            receiver.setName(newName);
            session.update(receiver);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean updateExpense(int num, String newPayDate, int newReceiver, double newValue) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Expense expense = session.get(Expense.class, num);
            if (expense == null) return false;
            expense.setPayDate(newPayDate);
            expense.setReceiver(newReceiver);
            expense.setValue(newValue);
            session.update(expense);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean deleteReceiver(int num) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Receiver receiver = session.get(Receiver.class, num);
            if (receiver == null) return false;
            session.delete(receiver);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean deleteExpense(int num) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Expense expense = session.get(Expense.class, num);
            if (expense == null) return false;
            session.delete(expense);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
        return true;
    }
}
