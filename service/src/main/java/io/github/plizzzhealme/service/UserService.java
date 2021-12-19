package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.util.ServiceUtil;

public class UserService {

    protected UserService() {
    }

    public User authorize(String email, String password) {
        int passwordHash = ServiceUtil.passwordToHash(password);
        UserDao userDao = DaoFactory.getUserDao();
        password = null;

        try {
            return userDao.authorize(email, passwordHash);
        } catch (DaoException e) {
            return null;
        }
    }

    public static boolean register(User user, String password) {
        return false;
    }
}
