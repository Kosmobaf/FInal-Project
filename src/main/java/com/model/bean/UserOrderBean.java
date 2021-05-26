package com.model.bean;

import com.model.entity.Entity;

/**
 * Provide records for virtual table: users_tariff
 */
public class UserOrderBean extends Entity {

    private long userId;
    private long tariffId;
    private boolean isActive;
    private String nameService;
    private String nameTariff;
    private String dateAdd;

    @Override
    public String toString() {
        return "UserOrderTariffBean{" +
                "user_id=" + userId +
                ", tariff_id=" + tariffId +
                ", isActive=" + isActive +
                ", nameService='" + nameService + '\'' +
                ", nameTariff='" + nameTariff + '\'' +
                ", dateAdd='" + dateAdd + '\'' +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTariffId() {
        return tariffId;
    }

    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public UserOrderBean(Long id, long userId, long tariffId, boolean isActive, String nameService, String nameTariff, String dateAdd) {
        super(id);
        this.userId = userId;
        this.tariffId = tariffId;
        this.isActive = isActive;
        this.nameService = nameService;
        this.nameTariff = nameTariff;
        this.dateAdd = dateAdd;
    }
}
