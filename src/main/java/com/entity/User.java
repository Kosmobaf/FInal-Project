package com.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private String typeUser;
    private Double cash;
    private List<Services> servicesList;
    public User() {
    }
    public User(String login, String password, String typeUser) {
        this.login = login;
        this.password = password;
        this.typeUser = typeUser;
    }

    public User(int id, String login, String password, String typeUser, Double cash, List<Services> servicesList) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.typeUser = typeUser;
        this.cash = cash;
        this.servicesList = servicesList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                typeUser.equals(user.typeUser) &&
                cash.equals(user.cash) &&
                servicesList.equals(user.servicesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, typeUser, cash, servicesList);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User " +
                "id=" + id +
                ", login='" + login + '\'' +
                ", typeUser='" + typeUser + '\'' +
                ", cash=" + cash +
                ", servicesList=" + servicesList;
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

    public List<Services> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }
}
