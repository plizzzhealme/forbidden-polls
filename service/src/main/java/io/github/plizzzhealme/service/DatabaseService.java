package io.github.plizzzhealme.service;

import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

public class DatabaseService {

    protected DatabaseService() {
    }

    public void connectToDatabase() {
        try {
            ConnectionPool.INSTANCE.initPoolData();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromDatabase() {
        try {
            ConnectionPool.INSTANCE.dispose();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
