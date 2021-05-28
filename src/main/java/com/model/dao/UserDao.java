package com.model.dao;

import com.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);
}

