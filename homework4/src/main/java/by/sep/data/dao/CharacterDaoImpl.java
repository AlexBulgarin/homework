package by.sep.data.dao;

import by.sep.data.pojos.character.Character;
import by.sep.data.pojos.character.CharacterStatistics;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CharacterDaoImpl implements CharacterDao {
    private final SessionFactory sessionFactory;

    public CharacterDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Character character) {
        Transaction transaction = null;
        Integer savedId = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            savedId = (Integer) session.save(character);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Game character was not saved");
        }
        System.out.println("Game character was saved");
        return savedId;
    }

    @Override
    public Character read(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Character.class, id);
        }
    }

    @Override
    public boolean update(int id, String newName, CharacterStatistics newStatistics) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Character character = session.get(Character.class, id);
            if (character == null) return false;
            character.setCharacterName(newName);
            character.setCharacterStatistics(newStatistics);
            session.update(character);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Game character was not updated");
            return false;
        }
        System.out.println("Game character was updated");
        return true;
    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Character character = session.get(Character.class, id);
            if (character == null) return false;
            session.remove(character);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Game character was not deleted");
            return false;
        }
        System.out.println("Game character was deleted");
        return true;
    }
}
