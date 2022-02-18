package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.service.exception.*;

import java.util.List;

public interface UserService {

    User signIn(String email, String password)
            throws ServiceException, InvalidCredentialsException, UserBlockedException;

    void signUp(User user) throws ServiceException, ValidatorException, EmailIsBusyException;

    User readUserInfo(int id) throws ServiceException;

    void updateUserInfo(User user) throws ServiceException, ValidatorException;

    List<User> search(SearchCriteria criteria, int limit, int offset) throws ServiceException;

    String readUserRole(int userId) throws ServiceException;

    void blockUser(int id) throws ServiceException;
}
