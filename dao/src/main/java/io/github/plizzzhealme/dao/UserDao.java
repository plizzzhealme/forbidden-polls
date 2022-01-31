package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.exception.DaoException;

import java.util.List;

public interface UserDao {

    boolean create(User user, String password) throws DaoException;

    User find(int id) throws DaoException;

    User signIn(String email, String password) throws DaoException;

    List<User> search(SearchCriteria criteria) throws DaoException;

    boolean isPresent(String email) throws DaoException;
}
