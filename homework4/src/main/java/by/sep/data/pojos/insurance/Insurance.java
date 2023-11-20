package by.sep.data.pojos.insurance;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_INSURANCE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Insurance {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String insuranceId;
    @Column(nullable = false)
    private String insurantName;
    @Embedded
    private InsuranceInfo insuranceInfo;

    public Insurance() {
    }

    public Insurance(String insuranceId, String insurantName, InsuranceInfo insuranceInfo) {
        this.insuranceId = insuranceId;
        this.insurantName = insurantName;
        this.insuranceInfo = insuranceInfo;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsurantName() {
        return insurantName;
    }

    public void setInsurantName(String insurantName) {
        this.insurantName = insurantName;
    }

    public InsuranceInfo getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(InsuranceInfo insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Insurance insurance = (Insurance) o;

        if (!Objects.equals(insuranceId, insurance.insuranceId))
            return false;
        if (!Objects.equals(insurantName, insurance.insurantName))
            return false;
        return Objects.equals(insuranceInfo, insurance.insuranceInfo);
    }

    @Override
    public int hashCode() {
        int result = insuranceId != null ? insuranceId.hashCode() : 0;
        result = 31 * result + (insurantName != null ? insurantName.hashCode() : 0);
        result = 31 * result + (insuranceInfo != null ? insuranceInfo.hashCode() : 0);
        return result;
    }
}
