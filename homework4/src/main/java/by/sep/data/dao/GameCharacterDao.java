package by.sep.data.dao;

import by.sep.data.pojos.GameCharacter;

public interface GameCharacterDao {
    boolean save(GameCharacter gameCharacter);

    GameCharacter get(int id);

    boolean delete(int id);

}
