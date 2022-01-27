package io.github.plizzzhealme.service.exception.impl;

import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.ConnectionService;
import io.github.plizzzhealme.service.exception.ServiceException;

public class ConnectionServiceImpl implements ConnectionService {

    public void connect() throws ServiceException {
        try {
            DaoFactory.INSTANCE.getConnectionDao().connect();
        } catch (DaoException e) {
            throw new ServiceException("Connection error.", e);
        }
    }

    public void disconnect() {
        DaoFactory.INSTANCE.getConnectionDao().disconnect();
    }
}
