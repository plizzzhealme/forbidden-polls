package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.service.exception.EmailIsBusyException;
import io.github.plizzzhealme.service.exception.ServiceException;
import io.github.plizzzhealme.service.exception.ValidatorException;

public interface UserService {

    User signIn(String email, String password) throws ServiceException;

    void signUp(User user) throws ServiceException, ValidatorException, EmailIsBusyException;

    User readUserInfo(int id) throws ServiceException;
}
