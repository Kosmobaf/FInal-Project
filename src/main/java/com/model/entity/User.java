package com.model.entity;

import com.model.Role;

import java.math.BigDecimal;

public class User extends Entity {
    public User() {
    }

    private String login;
    private String password;
    private Role role = Role.USER;
    private BigDecimal cash;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", cash=" + cash +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public User(Long id, String login, String password, String role, BigDecimal cash) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = Role.valueOf(role.toUpperCase());
        this.cash = cash;
    }
}
