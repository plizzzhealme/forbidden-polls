package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.Category;
import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {

    List<Category> findAll() throws ServiceException;
}
