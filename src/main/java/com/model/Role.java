package com.model;

import com.model.entity.User;

public enum Role {
    ADMIN, USER, UNKNOWN;

    public static Role getRole(User user) {
        String role = user.getRole().getName();
        return Role.valueOf(role);
    }

    public String getName() {
        return name().toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
