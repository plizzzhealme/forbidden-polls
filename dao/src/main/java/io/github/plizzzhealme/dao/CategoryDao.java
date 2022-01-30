package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.exception.DaoException;

import java.util.List;

public interface CategoryDao {

    List<String> findAll() throws DaoException;

    int findId(String category) throws DaoException;

    void create(String category) throws DaoException;

    boolean isPresent(String categoryName) throws DaoException;
}
