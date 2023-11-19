package by.sep.data.pojos.character;

public class CharacterStatistics {
    private Integer healthPoints;
    private Integer strength;
    private Integer intellect;

    public CharacterStatistics() {
    }

    public CharacterStatistics(Integer healthPoints, Integer strength, Integer intellect) {
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.intellect = intellect;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public void setIntellect(Integer intellect) {
        this.intellect = intellect;
    }
}
