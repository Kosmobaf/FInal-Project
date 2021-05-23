package com.model;

/**
 * Holder for fields names of DB tables.
 *
 * @author Lytvynuk V.
 *
 */
public final class Fields {

    // entities
    public static final String ENTITY__ID = "id";

    public static final String USER__LOGIN = "login";
    public static final String USER__PASSWORD = "password";
    public static final String USER__ROLE = "role";
    public static final String USER__CASH = "cash";
    public static final String USER__STATUS = "status";

    public static final String TARIFF__NAME_TARIFF = "nameTariff";
    public static final String TARIFF__ID_SERVICE = "id_service";
    public static final String TARIFF__COST = "cost";

    public static final String SERVICES__NAME_SERVICE = "nameService";
    // TODO virtual
    public static final String USERS_SERVICES_TARIFF__USER_ID = "userID";
    public static final String USERS_SERVICES_TARIFF__SERVICES_ID = "tariffID";
    public static final String USERS_SERVICES_TARIFF__TARIFF_ID = "serviceID";

}