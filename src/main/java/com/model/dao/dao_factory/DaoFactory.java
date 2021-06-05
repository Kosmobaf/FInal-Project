package com.model.dao.dao_factory;


import com.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ServiceDao createServiceDao();

    public abstract TariffDao createTariffDao();

    public abstract UserDao createUserDao();

    public abstract UserOrderDao createUserOrderDao();


    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
