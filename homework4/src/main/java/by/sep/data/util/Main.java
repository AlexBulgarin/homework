package by.sep.data.util;

import by.sep.data.dao.GameCharacterDaoImpl;
import by.sep.data.pojos.GameCharacter;
import by.sep.data.pojos.GameCharacterStatistics;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setName("sephir");
        GameCharacterStatistics gameCharacterStatistics = new GameCharacterStatistics();
        gameCharacterStatistics.setHealthPoints(500);
        gameCharacterStatistics.setStrength(25);
        gameCharacterStatistics.setIntellect(36);
        gameCharacter.setGameCharacterStatistics(gameCharacterStatistics);
        GameCharacterDaoImpl dao = GameCharacterDaoImpl.getInstance(Hw4SessionFactory.getSessionFactory());
        dao.save(gameCharacter);
        System.out.println(dao.get(1));
        Session session = Hw4SessionFactory.getSessionFactory().openSession();
        session.refresh(gameCharacter);
        System.out.println("ID - " + session.getIdentifier(gameCharacter));
        dao.delete(1);
    }
}
