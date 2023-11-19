package by.sep.data.pojos.character;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Mage extends Character {
    private Integer manaPoints;

    public Mage() {
    }

    public Mage(Integer characterId, String characterName, CharacterStatistics characterStatistics, Integer manaPoints) {
        super(characterId, characterName, characterStatistics);
        this.manaPoints = manaPoints;
    }

    public Integer getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(Integer manaPoints) {
        this.manaPoints = manaPoints;
    }
}
