package com.model.dao.mapper;

import com.model.Fields;
import com.model.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TariffMapper implements ObjectMapper<Tariff>{
    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {
        return new Tariff(
                rs.getLong(Fields.ENTITY__ID),
                rs.getLong(Fields.TARIFF__ID_SERVICE),
                rs.getString(Fields.TARIFF__NAME_TARIFF),
                rs.getDouble(Fields.TARIFF__COST)
        );
    }
}
