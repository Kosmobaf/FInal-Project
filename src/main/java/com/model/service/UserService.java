package com.model.service;

import com.model.bean.UserOrderBean;
import com.model.builder.UserBuilder;
import com.model.dao.DaoFactory;
import com.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        return daoFactory.createUserDao().findAll();
    }

    public List<UserOrderBean> getUserOrders(Long id) {
        return daoFactory.createUserOrderDao().findAllUserOrdersByIdUser(id);
    }

    public User getUser(Long id) {
        return daoFactory.createUserDao().findById(id);
    }

    public void createUser(String login, String password) {
        UserBuilder builder = new UserBuilder();
        builder.setLogin(login);
        builder.setPassword(password);
        daoFactory.createUserDao().create(
                builder.getResult());
    }

    /*
    Show Optional
     */
    public boolean userIsExist(String login, String password) {

        Optional<User> user = daoFactory.createUserDao().findAll().stream().
                filter(user1 -> user1.getLogin().equals(login)).findFirst();
        if (user.isPresent()) {
            User user1 = user.get();
            return password.equals(user1.getPassword());
        }
        return false;
    }

    public Long getUserId(String login) {
        return daoFactory.createUserDao().findByLogin(login).getId();
    }
}
