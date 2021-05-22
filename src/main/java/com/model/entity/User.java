package com.model.entity;

import com.model.Role;
import com.model.Status;

public class User extends Entity {
    public User() {
    }

    private String login;
    private String password;
    private Role role;
    private Double cash;
    private Status status;

    public User(Long id, String login, String password, Role role, Double cash, Status status) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.cash = cash;
        this.status = status;
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = Role.valueOf(role);
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


    public void setRole(Role role) {
        this.role = role;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", cash=" + cash +
                ", status=" + status +
                '}';
    }
}
