package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

    public User authorize(String email, String password) throws ServiceException {

        UserDao userDao = DaoFactory.INSTANCE.getUserDao();

        try {
            return userDao.authorize(email, password);
        } catch (DaoException e) {
            throw new ServiceException("Authorization error", e);
        }
    }

    public boolean register(User user, String password) throws ServiceException {
        UserDao userDao = DaoFactory.INSTANCE.getUserDao();

        try {
            return userDao.create(user, password);
        } catch (DaoException e) {
            throw new ServiceException("Registration error", e);
        }
    }

    @Override
    public User read(int id) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getUserDao().read(id);
        } catch (DaoException e) {
            throw new ServiceException("Error getting user info", e);
        }
    }
}
