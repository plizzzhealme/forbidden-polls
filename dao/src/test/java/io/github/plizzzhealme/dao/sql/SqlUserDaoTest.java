package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlUserDaoTest {

    private final UserDao userDao = DaoFactory.INSTANCE.getUserDao();

    @BeforeAll
    static void connect() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @AfterAll
    static void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }


    @Test
    void isPresentExistingUser() throws DaoException {
        assertTrue(userDao.isPresent("plizzz.healme@gmail.com"));
    }

    @Test
    void isPresentNonExistentUser() throws DaoException {
        assertFalse(userDao.isPresent("pliz.healme@gmail.com"));
    }

    @Test
    void isPresentNull() throws DaoException {
        assertFalse(userDao.isPresent(null));
    }

    @Test
    void createValidUser() throws DaoException {
        User user = new User();

        user.setEmail("user@mail.com");
        user.setPassword("Simple#666");
        user.setUserRole(User.USER);
        user.setCountry("belarus");
        user.setGender(User.OTHER);
        userDao.create(user);

        assertTrue(userDao.isPresent("user@mail.com"));
    }

    @Test
    void createInvalidUser() {
        User user = new User();

        user.setEmail("user@mail.com");
        user.setPassword("Simple#666");
        user.setUserRole(User.USER);
        user.setCountry("belarus");
        user.setGender("invalid role");

        assertThrows(DaoException.class, () -> userDao.create(user));
    }

    @Test
    void createNullUser() {
        assertThrows(DaoException.class, () -> userDao.create(null));
    }

    @Test
    void update() {
    }

    @Test
    void find() {
    }

    @Test
    void signIn() {
    }

    @Test
    void search() {
    }


}
