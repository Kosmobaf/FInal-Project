package com.model.dao.mapper;

import com.model.Fields;
import com.model.Role;
import com.model.Status;
import com.model.builder.UserBuilder;
import com.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        UserBuilder builder = new UserBuilder();
        builder.setId(rs.getLong(Fields.ENTITY__ID));
        builder.setLogin(rs.getString(Fields.USER__LOGIN));
        builder.setPassword(rs.getString(Fields.USER__PASSWORD));
        builder.setRole(Role.valueOf(rs.getString(Fields.USER__ROLE)));
        builder.setCash(rs.getDouble(Fields.USER__CASH));
        builder.setStatus(Status.valueOf(rs.getString(Fields.USER__STATUS)));
        return builder.getResult();
    }

    @Override
    public User makeUnique(Map<Long, User> cache,
                           User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
