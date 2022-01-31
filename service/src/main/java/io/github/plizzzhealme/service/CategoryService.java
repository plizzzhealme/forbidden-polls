package io.github.plizzzhealme.service;

import io.github.plizzzhealme.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {

    List<String> findAll() throws ServiceException;

    int findId(String category) throws ServiceException;
}
