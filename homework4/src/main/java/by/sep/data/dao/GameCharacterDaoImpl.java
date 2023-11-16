package by.sep.data.dao;

import by.sep.data.pojos.GameCharacter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GameCharacterDaoImpl implements GameCharacterDao {
    private static GameCharacterDaoImpl instance;
    private final SessionFactory sessionFactory;

    private GameCharacterDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    public static GameCharacterDaoImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new GameCharacterDaoImpl(sessionFactory);
        }
        return instance;
    }

    @Override
    public boolean save(GameCharacter gameCharacter) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(gameCharacter);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Game character was not saved");
            return false;
        }
        System.out.println("Game character was saved");
        return true;
    }

    @Override
    public GameCharacter get(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(GameCharacter.class, id);
        }
    }

        @Override
        public boolean delete ( int id){
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                GameCharacter gameCharacter = session.get(GameCharacter.class, id);
                session.remove(gameCharacter);
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
