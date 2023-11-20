package by.sep.data.pojos.insurance;

public class InsuranceInfo {
    private Double insurancePrice;
    private Integer insuranceDuration;

    public InsuranceInfo() {
    }

    public InsuranceInfo(Double insurancePrice, Integer insuranceDuration) {
        this.insurancePrice = insurancePrice;
        this.insuranceDuration = insuranceDuration;
    }

    public Double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Integer getInsuranceDuration() {
        return insuranceDuration;
    }

    public void setInsuranceDuration(Integer insuranceDuration) {
        this.insuranceDuration = insuranceDuration;
    }
}
