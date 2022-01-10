package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.ConnectionDao;

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
