package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.exception.DaoException;

public interface UserDao {

    void create(User user) throws DaoException;

    void update(User user) throws DaoException;

    User find(int id) throws DaoException;

    User signIn(String email, String password) throws DaoException;

    boolean isPresent(String email) throws DaoException;
}
