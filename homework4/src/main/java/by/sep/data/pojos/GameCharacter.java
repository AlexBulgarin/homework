package by.sep.data.pojos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GameCharacter {
    @Id
    @GeneratedValue(generator = "system-increment")
    @GenericGenerator(name = "system-increment", strategy = "increment")
    private Integer id;

    private String name;
    @Embedded
    private GameCharacterStatistics gameCharacterStatistics;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameCharacterStatistics getGameCharacterStatistics() {
        return gameCharacterStatistics;
    }

    public void setGameCharacterStatistics(GameCharacterStatistics gameCharacterStatistics) {
        this.gameCharacterStatistics = gameCharacterStatistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameCharacter that = (GameCharacter) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(gameCharacterStatistics, that.gameCharacterStatistics);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gameCharacterStatistics != null ? gameCharacterStatistics.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gameCharacterStatistics=" + gameCharacterStatistics +
                '}';
    }
}
