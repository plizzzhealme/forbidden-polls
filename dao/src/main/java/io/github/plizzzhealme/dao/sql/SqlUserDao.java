package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.SearchCriteria;
import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;

public class SqlUserDao implements UserDao {

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public User read(int id) throws DaoException {
        return null;
    }

    @Override
    public boolean update(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }

    @Override
    public User search(SearchCriteria criteria) {
        return null;
    }
}
