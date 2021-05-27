package com.model.service;

import com.model.bean.UserOrderBean;
import com.model.builder.UserBuilder;
import com.model.dao.DaoFactory;
import com.model.entity.User;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
         return daoFactory.createUserDao().findAll();
    }
    public List<UserOrderBean> getUserOrders(Long id){
       return daoFactory.createUserOrderDao().findAllUserOrdersByIdUser(id);
    }
    public User getUser(Long id){
        return daoFactory.createUserDao().findById(id);
    }

    public void createUser(String login, String password) {
        UserBuilder builder = new UserBuilder();
        builder.setLogin(login);
        builder.setPassword(password);
        daoFactory.createUserDao().create(
                builder.getResult());
    }
}
