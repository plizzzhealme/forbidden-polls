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
    void correctAuthorization() {
        String email = "plizzz.healme@gmail.com";
        int passwordHash = "1q2w3e".hashCode();

        User expected;
        User actual;

        expected = new User();
        expected.setId(2);
        expected.setEmail(email);
        expected.setName("Dzianis");
        expected.setCountry("Belarus");
        expected.setGender("male");
        expected.setUserRole("admin");

        try {
            actual = DaoFactory.getUserDao().authorize(email, passwordHash);
        } catch (DaoException e) {
            actual = null;
        }

        assertEquals(expected, actual);
    }

    @Test
    void readWithExistingID() {
        int id = 2;
        User expected;
        User actual;

        expected = new User();
        expected.setId(2);
        expected.setEmail("plizzz.healme@gmail.com");
        expected.setName("Dzianis");
        expected.setCountry("Belarus");
        expected.setGender("male");
        expected.setUserRole("admin");

        try {
            actual = DaoFactory.getUserDao().read(id);
        } catch (DaoException e) {
            actual = null;
        }

        assertEquals(expected, actual);
    }

    @Test
    void create() {
        User user = new User();
        user.setId(2);
        user.setEmail("plizzz.healme@gmail.com");
        user.setName("Dzianis");
        user.setCountry("Belarus");
        user.setGender("male");
        user.setUserRole("admin");

        UserDao dao = DaoFactory.getUserDao();
        try {
            dao.create(user, "1q2w3e");
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
