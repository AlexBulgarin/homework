package by.sep.data.dao;

import by.sep.data.pojos.character.Character;
import by.sep.data.pojos.character.CharacterStatistics;

public interface CharacterDao {
    Integer create(Character character);

    Character read(int id);

    boolean update(int id, String newName, CharacterStatistics newStatistics);

    boolean delete(int id);

}
