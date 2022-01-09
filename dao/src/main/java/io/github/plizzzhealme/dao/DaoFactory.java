package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.sql.SqlUserDao;

public enum DaoFactory {

    INSTANCE;

    private final UserDao userDao = new SqlUserDao();

    public UserDao getUserDao() {
        return userDao;
    }
}
