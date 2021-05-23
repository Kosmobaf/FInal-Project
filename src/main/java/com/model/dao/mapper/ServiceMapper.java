package com.model.dao.mapper;

import com.model.Fields;
import com.model.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ServiceMapper implements ObjectMapper<Service>{
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        return new Service(
                rs.getLong(Fields.ENTITY__ID),
                rs.getString(Fields.SERVICES__NAME_SERVICE)
        );
    }

    @Override
    public Service makeUnique(Map<Long, Service> cache, Service teacher) {
        return null;
    }
}