package by.sep.data.dao;

import by.sep.data.pojos.insurance.Insurance;
import by.sep.data.pojos.insurance.InsuranceInfo;

public interface InsuranceDao {
    String create(Insurance insurance);

    Insurance read(String id);

    boolean update(String id, String newName, InsuranceInfo newInsuranceInfo);

    boolean delete(String id);
}
