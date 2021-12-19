package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.exception.DaoException;

@SuppressWarnings("unused")
public interface Dao<T> {

    boolean create(T t);

    T read(int id) throws DaoException;

    boolean update(T t) throws DaoException;

    boolean delete(int id) throws DaoException;
}
