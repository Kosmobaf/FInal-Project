package com.model.dao.mapper;

import com.model.Fields;
import com.model.entity.Service;
import com.model.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TariffMapper implements ObjectMapper<Tariff>{
    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {
        return new Tariff(
                rs.getLong(Fields.ENTITY__ID),
                new Service(), //rs.getString(Fields.TARIFF__ID_SERVICE),
                rs.getString(Fields.TARIFF__NAME_TARIFF),
                rs.getBigDecimal(Fields.TARIFF__COST)
        );
    }

    @Override
    public Tariff makeUnique(Map<Long, Tariff> cache, Tariff entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
