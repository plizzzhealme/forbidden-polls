package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    @BeforeAll
    static void start() {
        try {
            ConnectionPool.INSTANCE.initPoolData();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void finish() {
        ConnectionPool.INSTANCE.dispose();
    }

    @Test
    void authorizeWithCorrectData() throws ServiceException {
        String email = "plizzz.healme@gmail.com";
        String password = "1q2w3e";

        User expected;
        User actual;

        expected = new User();
        expected.setId(2);
        expected.setEmail(email);
        expected.setName("Dzianis");
        expected.setCountry("Belarus");
        expected.setGender("male");
        expected.setUserRole("admin");

        actual = new UserService().authorize(email, password);

        assertEquals(expected, actual);
    }
}