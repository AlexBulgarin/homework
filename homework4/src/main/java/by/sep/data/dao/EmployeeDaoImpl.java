package by.sep.data.dao;

import by.sep.data.pojos.employee.Employee;
import by.sep.data.pojos.employee.EmployeeDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmployeeDaoImpl implements EmployeeDao {
    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Employee employee) {
        Transaction transaction = null;
        Long savedId = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            savedId = (Long) session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Employee was not saved");
        }
        System.out.println("Employee was saved");
        return savedId;
    }

    @Override
    public Employee read(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public boolean update(long id, String newFirstName, String newLastName, EmployeeDetails newEmployeeDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee == null) return false;
            employee.setEmployeeFirstName(newFirstName);
            employee.setEmployeeLastName(newLastName);
            employee.setEmployeeDetails(newEmployeeDetails);
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Employee was not updated");
            return false;
        }
        System.out.println("Employee was updated");
        return true;
    }

    @Override
    public boolean delete(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee == null) return false;
            session.remove(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!= null) transaction.rollback();
            System.out.println("Employee was not deleted");
            return false;
        }
        System.out.println("Employee was deleted");
        return true;
    }
}
