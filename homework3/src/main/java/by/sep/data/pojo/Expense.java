package by.sep.data.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(generator = "system-increment")
    @GenericGenerator(name = "system-increment", strategy = "increment")
    @Column(name = "num")
    private Integer num;

    @Column(name = "paydate")
    private String payDate;

    @Column(name = "receiver")
    private Integer receiver;

    @Column(name = "value")
    private Double value;

    public Expense() {
    }

    public Expense(Integer num, String payDate, Integer receiver, Double value) {
        this.num = num;
        this.payDate = payDate;
        this.receiver = receiver;
        this.value = value;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (!Objects.equals(num, expense.num)) return false;
        if (!Objects.equals(payDate, expense.payDate)) return false;
        if (!Objects.equals(receiver, expense.receiver)) return false;
        return Objects.equals(value, expense.value);
    }

    @Override
    public int hashCode() {
        int result = num != null ? num.hashCode() : 0;
        result = 31 * result + (payDate != null ? payDate.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "num=" + num +
                ", payDate='" + payDate + '\'' +
                ", receiver=" + receiver +
                ", value=" + value +
                '}';
    }
}
