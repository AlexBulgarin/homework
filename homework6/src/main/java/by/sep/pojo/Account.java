package by.sep.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

import java.util.Objects;

@Entity
@Table(name = "t_account")
public class Account {
    @Id
    @GenericGenerator(name = "uuid", type = UuidGenerator.class)
    @Column(name = "iban")
    private String iban;
    @Column(name = "type")
    private String type;
    @Column(name = "balance")
    private Double balance;

    public Account() {
    }

    public Account(String iban, String type, Double balance) {
        this.iban = iban;
        this.type = type;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!Objects.equals(iban, account.iban)) return false;
        if (!Objects.equals(type, account.type)) return false;
        return Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        int result = iban != null ? iban.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
