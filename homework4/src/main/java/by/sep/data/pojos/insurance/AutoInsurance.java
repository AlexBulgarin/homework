package by.sep.data.pojos.insurance;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "T_AUTO_INSURANCE")
@PrimaryKeyJoinColumn(name = "insuranceId")
public class AutoInsurance extends Insurance {
    private String vehicleModel;
    private String vehicleNumber;

    public AutoInsurance() {
    }

    public AutoInsurance(String insuranceId, String insurantName, InsuranceInfo insuranceInfo, String vehicleModel, String vehicleNumber) {
        super(insuranceId, insurantName, insuranceInfo);
        this.vehicleModel = vehicleModel;
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
