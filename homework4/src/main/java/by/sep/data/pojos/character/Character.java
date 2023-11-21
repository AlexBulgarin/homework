package by.sep.data.pojos.character;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_CHARACTER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CHARACTER_TYPE", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("C")
public class Character {
    @Id
    @GeneratedValue(generator = "system-increment")
    @GenericGenerator(name = "system-increment", strategy = "increment")
    private Integer characterId;
    @Column(nullable = false)
    private String characterName;
    @Embedded
    private CharacterStatistics characterStatistics;

    protected Character() {
    }

    protected Character(Integer characterId, String characterName, CharacterStatistics characterStatistics) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.characterStatistics = characterStatistics;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public CharacterStatistics getCharacterStatistics() {
        return characterStatistics;
    }

    public void setCharacterStatistics(CharacterStatistics characterStatistics) {
        this.characterStatistics = characterStatistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (!Objects.equals(characterId, character.characterId))
            return false;
        if (!Objects.equals(characterName, character.characterName))
            return false;
        return Objects.equals(characterStatistics, character.characterStatistics);
    }

    @Override
    public int hashCode() {
        int result = characterId != null ? characterId.hashCode() : 0;
        result = 31 * result + (characterName != null ? characterName.hashCode() : 0);
        result = 31 * result + (characterStatistics != null ? characterStatistics.hashCode() : 0);
        return result;
    }
}
