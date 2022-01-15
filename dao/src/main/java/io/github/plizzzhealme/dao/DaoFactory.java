package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.sql.SqlConnectionDao;
import io.github.plizzzhealme.dao.sql.SqlUserDao;
import io.github.plizzzhealme.dao.sql.SqlUserRoleDao;
import io.github.plizzzhealme.dao.util.ConnectionDao;

public enum DaoFactory {

    INSTANCE;

    private final UserDao userDao = new SqlUserDao();
    private final ConnectionDao connectionDao = new SqlConnectionDao();
    private final UserRoleDao userRoleDao = new SqlUserRoleDao();

    public UserDao getUserDao() {
        return userDao;
    }

    public ConnectionDao getConnectionDao() {
        return connectionDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }
}
