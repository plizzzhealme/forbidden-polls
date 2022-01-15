package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.exception.DaoException;

public interface UserRoleDao {

    String read(int id) throws DaoException;

    int search(String userRoleName) throws DaoException;
}
