package by.sep.data.Task7;

import java.io.Serializable;

public class Expense implements Serializable {
    private Integer num;
    private String paydate;
    private Integer receiver;
    private Double value;

    public Expense() {
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "num=" + num +
                ", paydate='" + paydate + '\'' +
                ", receiver=" + receiver +
                ", value=" + value +
                '}';
    }
}
