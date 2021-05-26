package com.model.builder;

import com.model.bean.UserOrderBean;
import com.model.entity.Entity;

public class UserOrderBeanBuilder extends Entity {
    private long userId;
    private long tariffId;
    private boolean isActive;
    private String nameService;
    private String nameTariff;
    private String dateAdd;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public UserOrderBean getResult() {
        return new UserOrderBean(
                super.getId(),
                userId,
                tariffId,
                isActive,
                nameService,
                nameTariff,
                dateAdd);
    }
}
