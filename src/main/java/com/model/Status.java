package com.model;

import com.model.entity.User;

public enum Status {
    ACTIVE, BLOCKED;

    public static Status getStatus(User user) {
        String status = user.getStatus().getName();
        return Status.valueOf(status);
    }

    public String getName() {
        return name().toLowerCase();
    }
}
