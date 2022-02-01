package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.EmailIsBusyException;
import io.github.plizzzhealme.service.exception.ServiceException;
import io.github.plizzzhealme.service.exception.ValidatorException;
import io.github.plizzzhealme.service.validator.UserValidator;

public class UserServiceImpl implements UserService {

    public User signIn(String email, String password) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getUserDao().signIn(email, password);
        } catch (DaoException e) {
            throw new ServiceException("Authorization error", e);
        }
    }

    public void signUp(User user) throws ServiceException, ValidatorException, EmailIsBusyException {
        UserValidator.getInstance().validateUser(user);

        UserDao userDao = DaoFactory.INSTANCE.getUserDao();

        try {
            boolean userExists = userDao.isPresent(user.getEmail());

            if (userExists) {
                throw new EmailIsBusyException("Email " + user.getEmail() + " is busy.");
            } else {
                userDao.create(user);
            }
        } catch (DaoException e) {
            throw new ServiceException("Registration error.", e);
        }
    }

    @Override
    public User readUserInfo(int id) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getUserDao().find(id);
        } catch (DaoException e) {
            throw new ServiceException("Error getting user info", e);
        }
    }
}
