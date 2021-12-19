package io.github.plizzzhealme.service;

import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

public class DatabaseConnectionService {

    protected DatabaseConnectionService() {
    }

    public void connect() {
        try {
            ConnectionPool.INSTANCE.initPoolData();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }
}
