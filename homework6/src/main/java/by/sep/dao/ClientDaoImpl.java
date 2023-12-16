package by.sep.dao;

import by.sep.pojo.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ClientDaoImpl {
    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }


    public String create(Client client) {
        Session session = sessionFactory.getCurrentSession();
        return (String) session.save(client);
    }

    @Transactional(readOnly = true)
    public Client read(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }

    public boolean deletePersonById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Client client = session.get(Client.class, id); //Some work
        if (client == null) return false;
        session.delete(client);
        return true;
    }

}
