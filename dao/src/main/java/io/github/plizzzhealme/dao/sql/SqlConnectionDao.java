package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.dao.ConnectionDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

public class SqlConnectionDao implements ConnectionDao {

    @Override
    public void connect() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @Override
    public void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }
}
