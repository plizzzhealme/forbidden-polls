package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.exception.DaoException;

public interface UserDao {

    User read(int id) throws DaoException;

    User authorize(String email, int passwordHash) throws DaoException;
}
