package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.SearchCriteria;
import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.service.util.ServiceUtil;

public class Service {

    public static User authorise(String email, String password) {
        int passwordHash = ServiceUtil.passwordToHash(password);
        password = null;
        SearchCriteria criteriaWithEmail = new SearchCriteria();
        criteriaWithEmail.addParameter("email", email);

        User user;

        try {
            user = DaoFactory.getUserDao().search(criteriaWithEmail);
        } catch (DaoException e) {
            return null;
        }

        boolean isCorrectPassword = user.getPasswordHash() == passwordHash;
        passwordHash = 0;
        user.setPasswordHash(0);

        if (isCorrectPassword) {
            return user;
        } else {
            user.setPasswordHash(0);
            return null;
        }
    }

    public static boolean register(User user, String password) {
        return false;
    }
}
