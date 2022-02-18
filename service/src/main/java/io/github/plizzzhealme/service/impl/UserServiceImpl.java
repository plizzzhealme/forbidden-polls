package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.exception.EntityNotFoundException;
import io.github.plizzzhealme.dao.exception.InvalidPasswordException;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.*;
import io.github.plizzzhealme.service.validator.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {

    public User signIn(String email, String password)
            throws ServiceException, InvalidCredentialsException, UserBlockedException {

        try {
            User user = DaoFactory.INSTANCE.getUserDao().signIn(email, password);

            if (user.getUserRole().equals(User.BANNED_ROLE)) {
                throw new UserBlockedException();
            }
            return user;
        } catch (EntityNotFoundException | InvalidPasswordException e) {
            throw new InvalidCredentialsException("Invalid credentials");
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
        } catch (DaoException | EntityNotFoundException e) {
            throw new ServiceException("Error getting user info", e);
        }
    }

    @Override
    public void updateUserInfo(User user) throws ServiceException, ValidatorException {
        UserValidator userValidator = UserValidator.getInstance();

        if (userValidator.isValidName(user.getName())
                && userValidator.isValidEmail(user.getEmail())
                && userValidator.isValidGender(user.getGender())
                && userValidator.isValidCountyCode(user.getCountry())
                && userValidator.isValidBirthday(user.getBirthday())) {

            try {
                DaoFactory.INSTANCE.getUserDao().update(user);
            } catch (DaoException e) {
                throw new ServiceException("Failed to update user info", e);
            }
        } else {
            throw new ValidatorException("Invalid data, unable to update");
        }
    }

    @Override
    public List<User> search(SearchCriteria criteria, int limit, int offset) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getUserDao().search(criteria, limit, offset);
        } catch (DaoException e) {
            throw new ServiceException("Failed to search", e);
        }
    }

    @Override
    public String readUserRole(int userId) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getUserDao().readRole(userId);
        } catch (DaoException | EntityNotFoundException e) {
            throw new ServiceException("Failed to read user role", e);
        }
    }

    @Override
    public void blockUser(int userId) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.INSTANCE.getUserDao();
            String userRole = userDao.readRole(userId);

            if (!userRole.equals(User.ADMIN_ROLE)) {
                userDao.updateUserRole(User.BANNED_ROLE, userId);
            }
        } catch (DaoException | EntityNotFoundException e) {
            throw new ServiceException("Failed to block user", e);
        }
    }
}
