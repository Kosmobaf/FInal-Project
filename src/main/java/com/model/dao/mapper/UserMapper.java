package com.model.dao.mapper;

import com.model.Fields;
import com.model.builder.UserBuilder;
import com.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        UserBuilder builder = new UserBuilder();
        builder.setId(rs.getLong(Fields.ENTITY__ID));
        builder.setLogin(rs.getString(Fields.USER__LOGIN));
        builder.setPassword(rs.getString(Fields.USER__PASSWORD));
        builder.setRole(rs.getString(Fields.USER__ROLE));
        builder.setCash(rs.getBigDecimal(Fields.USER__CASH));
        return builder.getResult();
    }
}
