package by.sep.data.dao;

import by.sep.data.pojos.character.Character;
import by.sep.data.pojos.character.CharacterStatistics;
import by.sep.data.pojos.character.Mage;
import by.sep.data.pojos.character.Warrior;
import by.sep.data.util.Hw4TestDataSource;
import by.sep.data.util.Hw4TestSessionFactory;
import org.junit.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CharacterDaoImplTest {
    static CharacterDao dao;
    static Connection connection;
    int testWarriorId = 1;
    int testMageId = 2;
    String testWarriorName = "Test Warrior Name";
    String testMageName = "Test Mage Name";
    int testWarriorHealthPoints = 100;
    int testWarriorStrength = 10;
    int testWarriorIntellect = 5;
    int testMageHealthPoints = 80;
    int testMageStrength = 6;
    int testMageIntellect = 14;
    int testRagePoints = 80;
    int testManaPoints = 120;
    Character testWarrior = new Warrior(testWarriorId, testWarriorName,
            new CharacterStatistics(testWarriorHealthPoints, testWarriorStrength, testMageIntellect),
            testRagePoints);
    Character testMage = new Mage(testMageId, testMageName,
            new CharacterStatistics(testMageHealthPoints, testMageStrength, testMageHealthPoints),
            testManaPoints);

    @BeforeClass
    public static void beforeClass() throws Exception {
        dao = new CharacterDaoImpl(Hw4TestSessionFactory.getSessionFactory());
        connection = Hw4TestDataSource.getConnection();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        dao = null;
        connection.close();
    }

    @Before
    public void setUp() throws Exception {
        connection.createStatement().executeUpdate("INSERT INTO T_CHARACTER\n" +
                "(CHARACTER_TYPE, characterId, characterName, healthPoints, intellect, strength, ragePoints)\n" +
                "VALUES('W'," + testWarriorId + ",'" + testWarriorName + "'," +
                testWarriorHealthPoints + "," + testWarriorIntellect + "," +
                testWarriorStrength + "," + testRagePoints + ")");
        connection.createStatement().executeUpdate("INSERT INTO T_CHARACTER\n" +
                "(CHARACTER_TYPE, characterId, characterName, healthPoints, intellect, strength, manaPoints)\n" +
                "VALUES('M'," + testMageId + ",'" + testMageName + "'," +
                testMageHealthPoints + "," + testMageIntellect + "," +
                testMageStrength + "," + testManaPoints + ")");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement()
                .executeUpdate("truncate table T_CHARACTER");
    }

    @Test()
    public void create() throws SQLException {
        int id1 = dao.create(testWarrior);
        int id2 = dao.create(testMage);

        assertEquals(3, id1);
        assertEquals(4, id2);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM T_CHARACTER;");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(4, actualCount);
    }

    @Test
    public void read() {
        Warrior acquiredWarrior = (Warrior) dao.read(testWarriorId);
        Mage acquiredMage = (Mage) dao.read(testMageId);

        assertEquals(testWarriorId, acquiredWarrior.getCharacterId().intValue());
        assertEquals(testWarriorName, acquiredWarrior.getCharacterName());
        assertEquals(testWarriorHealthPoints, acquiredWarrior.getCharacterStatistics().getHealthPoints().intValue());
        assertEquals(testWarriorStrength, acquiredWarrior.getCharacterStatistics().getStrength().intValue());
        assertEquals(testWarriorIntellect, acquiredWarrior.getCharacterStatistics().getIntellect().intValue());
        assertEquals(testRagePoints, acquiredWarrior.getRagePoints().intValue());

        assertEquals(testMageId, acquiredMage.getCharacterId().intValue());
        assertEquals(testMageName, acquiredMage.getCharacterName());
        assertEquals(testMageHealthPoints, acquiredMage.getCharacterStatistics().getHealthPoints().intValue());
        assertEquals(testMageStrength, acquiredMage.getCharacterStatistics().getStrength().intValue());
        assertEquals(testMageIntellect, acquiredMage.getCharacterStatistics().getIntellect().intValue());
        assertEquals(testManaPoints, acquiredMage.getManaPoints().intValue());

    }

    @Test
    public void update() throws SQLException {
        String updatedTestName = "Updated Test Name";
        int updatedTestHealthPoints = 200;
        int updatedTestStrength = 20;
        int updatedTestIntellect = 20;
        dao.update(testWarriorId, updatedTestName, new CharacterStatistics(
                updatedTestHealthPoints, updatedTestStrength, updatedTestIntellect));
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM T_CHARACTER " +
                "WHERE characterId=" + testWarriorId + ";");
        resultSet.next();
        assertEquals(updatedTestName, resultSet.getString("characterName"));
        assertEquals(updatedTestHealthPoints, resultSet.getInt("healthPoints"));
        assertEquals(updatedTestStrength, resultSet.getInt("strength"));
        assertEquals(updatedTestIntellect, resultSet.getInt("intellect"));

    }

    @Test
    public void delete() throws SQLException {
        dao.delete(1);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM T_CHARACTER;");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(1, actualCount);
    }
}