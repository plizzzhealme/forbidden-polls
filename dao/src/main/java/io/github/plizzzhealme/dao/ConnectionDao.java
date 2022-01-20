package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.exception.DaoException;

public interface ConnectionDao {

    void connect() throws DaoException;

    void disconnect();
}
