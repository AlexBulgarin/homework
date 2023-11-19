package by.sep.data.pojos.character;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("W")
public class Warrior extends Character {
    private Integer ragePoints;

    public Warrior() {
    }

    public Warrior(Integer characterId, String characterName, CharacterStatistics characterStatistics, Integer ragePoints) {
        super(characterId, characterName, characterStatistics);
        this.ragePoints = ragePoints;
    }

    public Integer getRagePoints() {
        return ragePoints;
    }

    public void setRagePoints(Integer ragePoints) {
        this.ragePoints = ragePoints;
    }
}
