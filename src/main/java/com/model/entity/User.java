package com.model.entity;

import com.model.Role;
import com.model.Status;

import java.math.BigDecimal;

public class User extends Entity {

    private String login;
    private String password;
    private Role role = Role.USER;
    private BigDecimal cash;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
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


    /**
    * Use pattern Builder
     */
    public static class Builder extends Entity {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder id(Long id) {
            newUser.setId(id);
            return this;
        }

        public Builder cash(BigDecimal cash) {
            newUser.cash = cash;
            return this;
        }

        public Builder login(String login) {
            newUser.login = login;
            return this;
        }

        public Builder password(String password) {
            newUser.password = password;
            return this;
        }

        public Builder role(String role) {
            newUser.role = Role.valueOf(role.toUpperCase());
            return this;
        }
        public Builder status(String status){
            newUser.status = Status.valueOf(status.toUpperCase());
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
