package io.github.plizzzhealme.service.impl;

import io.github.plizzzhealme.dao.CategoryDao;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.CategoryService;
import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<String> findAll() throws ServiceException {
        CategoryDao categoryDao = DaoFactory.INSTANCE.getCategoryDao();

        try {
            return categoryDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("CategoryServiceImpl exception", e);
        }
    }

    @Override
    public int findId(String category) throws ServiceException {
        try {
            return DaoFactory.INSTANCE.getCategoryDao().findId(category);
        } catch (DaoException e) {
            throw new ServiceException("exc", e);
        }
    }
}
