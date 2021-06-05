package com.model.dao.dao_factory;

import com.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);
}

