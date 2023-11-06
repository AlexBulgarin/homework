package by.sep.data.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "receivers")
public class Receiver {

    @Id
    @GeneratedValue
    @Column(name = "num")
    private Integer num;

    @Column(name = "name")
    private String name;

    public Receiver() {
    }

    public Receiver(Integer num, String name) {
        this.num = num;
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receiver receiver = (Receiver) o;

        if (!Objects.equals(num, receiver.num)) return false;
        return Objects.equals(name, receiver.name);
    }

    @Override
    public int hashCode() {
        int result = num != null ? num.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
