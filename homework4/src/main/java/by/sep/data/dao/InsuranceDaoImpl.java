package by.sep.data.dao;

import by.sep.data.pojos.insurance.Insurance;
import by.sep.data.pojos.insurance.InsuranceInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InsuranceDaoImpl implements InsuranceDao {
    private final SessionFactory sessionFactory;

    public InsuranceDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String create(Insurance insurance) {
        Transaction transaction = null;
        String savedId = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            savedId = (String) session.save(insurance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Insurance was not saved");
        }
        System.out.println("Insurance was saved");
        return savedId;
    }

    @Override
    public Insurance read(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Insurance.class, id);
        }
    }

    @Override
    public boolean update(String id, String newName, InsuranceInfo newInsuranceInfo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Insurance insurance = session.get(Insurance.class, id);
            if (insurance == null) return false;
            insurance.setInsurantName(newName);
            insurance.setInsuranceInfo(newInsuranceInfo);
            session.update(insurance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Insurance was not updated");
            return false;
        }
        System.out.println("Insurance was updated");
        return true;
    }

    @Override
    public boolean delete(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Insurance insurance = session.get(Insurance.class, id);
            if (insurance == null) return false;
            session.remove(insurance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Insurance was not deleted");
            return false;
        }
        System.out.println("Insurance was deleted");
        return true;
    }
}
