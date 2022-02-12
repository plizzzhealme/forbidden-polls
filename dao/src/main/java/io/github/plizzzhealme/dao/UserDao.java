package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.exception.EntityNotFoundException;
import io.github.plizzzhealme.dao.exception.InvalidPasswordException;

public interface UserDao {

    void create(User user) throws DaoException;

    void update(User user) throws DaoException;

    User find(int id) throws DaoException, EntityNotFoundException;

    User signIn(String email, String password) throws DaoException, EntityNotFoundException, InvalidPasswordException;

    boolean isPresent(String email) throws DaoException;
}
