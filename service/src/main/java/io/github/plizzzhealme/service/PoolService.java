package io.github.plizzzhealme.service;

import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

public class PoolService {

    protected PoolService() {
    }

    public void initPoolData() {
        try {
            ConnectionPool.INSTANCE.initPoolData();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void clearPool() {
        ConnectionPool.INSTANCE.dispose();
    }
}
