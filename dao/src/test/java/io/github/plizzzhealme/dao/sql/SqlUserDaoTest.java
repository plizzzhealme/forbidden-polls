package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SqlUserDaoTest {

    @BeforeAll
    static void connect() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @AfterAll
    static void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }

    @Test
    void readWithExistingID() throws DaoException {
        UserDao userDao = DaoFactory.INSTANCE.getUserDao();

        int existingID = 1;
        String expected = "plizzz.healme@gmail.com";
        String actual = userDao.read(existingID).getEmail();

        assertEquals(expected, actual);
    }

    @Test
    void create() throws DaoException {
        UserDao dao = DaoFactory.INSTANCE.getUserDao();

        String password = "1q2w3e";
        User user = new User();
        user.setEmail("plizzzehesalme@gmail.com");
        user.setName("Dzianis");
        user.setCountry("Belarus");
        user.setGender("male");
        user.setUserRole("admin");

        boolean isCreated = dao.create(user, password);

        assertTrue(isCreated);
    }
}
