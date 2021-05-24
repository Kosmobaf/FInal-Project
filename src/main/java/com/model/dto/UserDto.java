package com.model.dto;

import com.model.Role;
import com.model.Status;
import com.model.entity.Service;
import com.model.entity.Tariff;

import java.util.Map;

public class UserDto {
    private long id;
    private String login;
    private String password;
    private Role role;
    private Double cash;
    private Status status;
    private Map<Service, Tariff> serviceTariffMap;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", cash=" + cash +
                ", status=" + status +
                ", serviceTariffMap=" + serviceTariffMap +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Map<Service, Tariff> getServiceTariffMap() {
        return serviceTariffMap;
    }

    public void setServiceTariffMap(Map<Service, Tariff> serviceTariffMap) {
        this.serviceTariffMap = serviceTariffMap;
    }
}
