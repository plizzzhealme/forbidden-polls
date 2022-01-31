package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.service.exception.ServiceException;

public interface UserService {

    User authorize(String email, String password) throws ServiceException;

    boolean register(User user, String password) throws ServiceException;

    User read(int id) throws ServiceException;
}
