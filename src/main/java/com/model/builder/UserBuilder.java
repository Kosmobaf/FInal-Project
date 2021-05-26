package com.model.builder;

import com.model.Role;
import com.model.entity.Entity;
import com.model.entity.User;

import java.math.BigDecimal;

public class UserBuilder extends Entity {
    private String login;
    private String password;
    private Role role;
    private BigDecimal cash;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public User getResult() {
        return new User(super.getId(), login, password, role, cash);
    }
}
