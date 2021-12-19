package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.sql.SqlUserDao;

public class DaoFactory {

    private static final UserDao userDao = new SqlUserDao();

    private DaoFactory() {
    }

    public static UserDao getUserDao() {
        return userDao;
    }
}
