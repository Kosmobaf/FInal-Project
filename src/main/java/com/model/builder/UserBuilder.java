package com.model.builder;

import com.model.Role;
import com.model.Status;
import com.model.entity.User;

public class UserBuilder {
    private long id;
    private String login;
    private String password;
    private Role role;
    private Double cash;
    private Status status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getResult() {
        return new User(id, login, password, role, cash, status);
    }
}
