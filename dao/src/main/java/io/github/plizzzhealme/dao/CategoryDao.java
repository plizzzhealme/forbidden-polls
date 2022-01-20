package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.bean.Category;
import io.github.plizzzhealme.dao.exception.DaoException;

import java.util.List;

public interface CategoryDao {

    List<Category> findAll() throws DaoException;
}
