package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.service.exception.ServiceException;

public class ConnectionServiceImpl {

    protected ConnectionServiceImpl() {
    }

    public void connect() throws ServiceException {
        try {
            ConnectionPool.INSTANCE.initPoolData();
        } catch (DaoException e) {
            throw new ServiceException("Connection error", e);
        }
    }

    public void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }
}
