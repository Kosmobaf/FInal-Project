package com.model.dao;

import java.util.List;


public interface GenericDao<T> extends AutoCloseable{
    void create (T entity);
    T findById(long id);
    List<T> findAll();
    void update(T entity);
    void delete(long id);

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
