package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;

public class UserService {

    protected UserService() {
    }

    public User authorize(String email, String password) {
        UserDao userDao = DaoFactory.getUserDao();

        try {
            return userDao.authorize(email, password);
        } catch (DaoException e) {
            return null;
        }
    }
}
