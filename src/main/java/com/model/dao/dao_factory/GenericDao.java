package com.model.dao.dao_factory;

import com.controller.MyException;

import java.util.List;


public interface GenericDao<T> extends AutoCloseable {
    void create(T entity) throws MyException;

    T findById(long id);

    List<T> findAll();

    void update(T entity) throws MyException;

    void delete(long id) throws MyException;

    void close();

    default void close(AutoCloseable autoCloseable) {
        try {
            if (autoCloseable != null) {
                autoCloseable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
