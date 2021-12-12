package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.sql.SqlUserDao;

public class DaoFactory {

    private static UserDao userDao;

    private DaoFactory() {
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new SqlUserDao();
        }

        return userDao;
    }
}
