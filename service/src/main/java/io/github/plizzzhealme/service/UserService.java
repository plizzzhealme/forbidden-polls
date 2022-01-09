package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.exception.ServiceException;

public class UserService {

    protected UserService() {
    }

    public User authorize(String email, String password) throws ServiceException {
        UserDao userDao = DaoFactory.getUserDao();

        try {
            return userDao.authorize(email, password);
        } catch (DaoException e) {
            throw new ServiceException("Authorization error", e);
        }
    }

    public boolean register(User user, String password) throws ServiceException {
        UserDao userDao = DaoFactory.getUserDao();

        try {
            return userDao.create(user, password);
        } catch (DaoException e) {
            throw new ServiceException("Registration error", e);
        }
    }
}
