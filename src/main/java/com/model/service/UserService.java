package com.model.service;

import com.model.dao.DaoFactory;
import com.model.dto.UserDto;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<UserDto> getAllUsersWithService() {
        return null;
    }
}
