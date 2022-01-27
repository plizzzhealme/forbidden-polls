package io.github.plizzzhealme.service.exception.impl;

import io.github.plizzzhealme.bean.Category;
import io.github.plizzzhealme.dao.CategoryDao;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.CategoryService;
import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() throws ServiceException {
        CategoryDao categoryDao = DaoFactory.INSTANCE.getCategoryDao();

        try {
            return categoryDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("CategoryServiceImpl exception", e);
        }
    }
}
