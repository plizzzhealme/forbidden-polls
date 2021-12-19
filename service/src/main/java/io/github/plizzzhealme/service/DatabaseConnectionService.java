package io.github.plizzzhealme.service;

import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

public class DatabaseConnectionService {

    protected DatabaseConnectionService() {
    }

    public void connect() {
        try {
            System.out.println("try");
            ConnectionPool.INSTANCE.initPoolData();
            System.out.println("done");
        } catch (DaoException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }
}
