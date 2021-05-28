package com.model;

import com.model.bean.UserOrderBean;

public enum Status {
    ACTIVE, BLOCKED;

    public static Status getRole(UserOrderBean orderBean) {
        String status = orderBean.getStatus();
        return Status.valueOf(status);
    }

    public String getName() {
        return name().toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
