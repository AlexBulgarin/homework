package by.sep.data.Task7;

import java.io.Serializable;

public class Receiver implements Serializable {
    private static final long serialVersionUID = -8314762500391495924L;
    private Integer num;
    private String name;

    public Receiver() {
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
    public String toString() {
        return "Receiver{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
