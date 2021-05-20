package com.entity;

public class User extends Entity {
    private String login;
    private String password;
    private String typeUser;
    private Double cash;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", typeUser='" + typeUser + '\'' +
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

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public User(String login, String password, String typeUser, Double cash) {
        this.login = login;
        this.password = password;
        this.typeUser = typeUser;
        this.cash = cash;
    }

    public User() {
    }
}
