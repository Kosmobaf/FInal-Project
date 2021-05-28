package com.model.dao.mapper;

import com.model.constants.TableName;
import com.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {

        return new User.Builder().
                id(rs.getLong(TableName.ENTITY__ID)).
                login(rs.getString(TableName.USER__LOGIN)).
                password(rs.getString(TableName.USER__PASSWORD)).
                role(rs.getString(TableName.USER__ROLE)).
                cash(rs.getBigDecimal(TableName.USER__CASH)).
                build();
    }
}
