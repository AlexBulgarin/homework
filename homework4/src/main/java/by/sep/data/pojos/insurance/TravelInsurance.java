package by.sep.data.pojos.insurance;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "T_TRAVEL_INSURANCE")
@PrimaryKeyJoinColumn(name = "insuranceId")
public class TravelInsurance extends Insurance {
    private String countryOfVisit;
    private String visaNumber;

    public TravelInsurance() {
    }

    public TravelInsurance(String insuranceId, String insurantName, InsuranceInfo insuranceInfo, String countryOfVisit, String visaNumber) {
        super(insuranceId, insurantName, insuranceInfo);
        this.countryOfVisit = countryOfVisit;
        this.visaNumber = visaNumber;
    }

    public String getCountryOfVisit() {
        return countryOfVisit;
    }

    public void setCountryOfVisit(String countryOfVisit) {
        this.countryOfVisit = countryOfVisit;
    }

    public String getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }
}
