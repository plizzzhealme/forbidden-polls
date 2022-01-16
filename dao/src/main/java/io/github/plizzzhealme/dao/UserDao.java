package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.exception.DaoException;

public interface UserDao {

    boolean create(User user, String password) throws DaoException;

    User read(int id) throws DaoException;

    int signIn(String email, String password) throws DaoException;

    boolean isPresent(String email) throws DaoException;
}
