package com.model.bean;

import com.model.Status;
import com.model.entity.Entity;

/**
 * Records for virtual table: users_tariff
 */
public class UserOrderBean extends Entity {

    private long userId;
    private long tariffId;
    private Status status;
    private String nameService;
    private String nameTariff;
    private String dateAdd;

    @Override
    public String toString() {
        return "UserOrderTariffBean{" +
                "user_id=" + userId +
                ", tariff_id=" + tariffId +
                ", isActive=" + status +
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

    public String getStatus() {
        return status.getName();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
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

    /**
     * Use pattern Builder
     */
    public static class Builder extends Entity {
        private UserOrderBean newUserOrderBean;

        public Builder() {
            newUserOrderBean = new UserOrderBean();
        }

        public Builder id(long id) {
            newUserOrderBean.setId(id);
            return this;
        }

        public Builder userId(long userId) {
            newUserOrderBean.userId = userId;
            return this;
        }

        public Builder tariffId(long tariffId) {
            newUserOrderBean.tariffId = tariffId;
            return this;
        }

        public Builder status(String active) {
            newUserOrderBean.status = Status.valueOf(active.toUpperCase());
            return this;
        }

        public Builder nameService(String nameService) {
            newUserOrderBean.nameService = nameService;
            return this;
        }

        public Builder nameTariff(String nameTariff) {
            newUserOrderBean.nameTariff = nameTariff;
            return this;
        }

        public Builder dateAdd(String dateAdd) {
            newUserOrderBean.dateAdd = dateAdd;
            return this;
        }

        public UserOrderBean build() {
            return newUserOrderBean;
        }
    }
}
