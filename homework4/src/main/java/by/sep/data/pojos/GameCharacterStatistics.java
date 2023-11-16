package by.sep.data.pojos;

public class GameCharacterStatistics {
    private Integer healthPoints;
    private Integer strength;
    private Integer intellect;

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

    @Override
    public String toString() {
        return "GameCharacterStatistics{" +
                "healthPoints=" + healthPoints +
                ", strength=" + strength +
                ", intellect=" + intellect +
                '}';
    }
}
