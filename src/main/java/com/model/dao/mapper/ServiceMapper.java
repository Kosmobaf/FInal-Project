package com.model.dao.mapper;

import com.model.Fields;
import com.model.entity.Service;
import com.model.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ServiceMapper implements ObjectMapper<Service>{
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        return new Service(
                rs.getLong(Fields.ENTITY__ID),
                rs.getString(Fields.SERVICES__NAME_SERVICE),
                null,
                null
        );
    }

    @Override
    public Service makeUnique(Map<Long, Service> cache, Service entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
