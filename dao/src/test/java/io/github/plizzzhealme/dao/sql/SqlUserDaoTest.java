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
    void readWithExistingID() {
        int id = 1;

        String expected = "plizzz.healme@gmail.com";
        String actual;


        try {
            User user = DaoFactory.INSTANCE.getUserDao().read(id);
            actual = user.getEmail();
        } catch (DaoException e) {
            e.printStackTrace();
            actual = null;
        }

        assertEquals(expected, actual);
    }

    @Test
    void create() {
        User user = new User();
        user.setEmail("plizzzehesalme@gmail.com");
        user.setName("Dzianis");
        user.setCountry("Belarus");
        user.setGender("male");
        user.setUserRole("admin");

        UserDao dao = DaoFactory.INSTANCE.getUserDao();

        boolean isCreated = false;

        try {
            isCreated = dao.create(user, "1q2w3e");
        } catch (DaoException e) {
            e.printStackTrace();
        }

        assertTrue(isCreated);
    }
}
