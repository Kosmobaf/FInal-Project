package com.model.service;

import com.model.bean.UserOrderBean;
import com.model.dao.DaoFactory;
import com.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        return daoFactory.createUserDao().findAll();
    }

    public List<UserOrderBean> getUserOrders(Long idUser) {
        return daoFactory.createUserOrderDao().findAllUserOrdersByIdUser(idUser);
    }

    public User getUser(Long id) {
        return daoFactory.createUserDao().findById(id);
    }

    public void createUser(String login, String password) {
        User user = new User.Builder().
                login(login).
                password(password).
                build();

        daoFactory.createUserDao().create(user);
    }

    /**
     * Show Optional
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

    public long getUserId(String login) {

        return daoFactory.createUserDao().findByLogin(login).getId();
    }

    public List<UserOrderBean> getOrdersForUser(String login) {
        List<UserOrderBean> userOrderBeans;
        long id = daoFactory.createUserDao().findByLogin(login).getId();
        userOrderBeans = daoFactory.createUserOrderDao().findAllUserOrdersByIdUser(id);
        return userOrderBeans;
    }
}
