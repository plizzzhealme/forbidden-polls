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
        user.setUserRole(User.USER_ROLE);
        user.setCountry("belarus");
        user.setGender(User.OTHER);
        userDao.create(user);

        assertTrue(userDao.isPresent("user@mail.com"));
    }

    @Test
    void createInvalidUser() {
        User user = new User();

        user.setEmail("user1@mail.com");
        user.setPassword("Password#666");
        user.setUserRole(User.USER_ROLE);
        user.setCountry("belarus");
        user.setGender("invalid role");

        assertThrows(DaoException.class, () -> userDao.create(user));
    }

    @Test
    void createUserWithDuplicateEmail() {
        User user = new User();

        user.setEmail("plizzz.healme@gmail.com");
        user.setPassword("Password#666");
        user.setUserRole(User.USER_ROLE);
        user.setCountry("belarus");
        user.setGender(User.MALE);

        assertThrows(DaoException.class, () -> userDao.create(user));
    }

    @Test
    void createNullUser() {
        assertThrows(DaoException.class, () -> userDao.create(null));
    }

    @Test
    void findExistingUser() throws DaoException {
        assertFalse(userDao.find(1).isNull());
    }

    @Test
    void findNonExistentUser() throws DaoException {
        assertTrue(userDao.find(-1).isNull());
    }

    @Test
    void correctUpdate() throws DaoException {
        User user = new User();
        user.setId(1);
        user.setEmail("plizzz.healme@gmail.com");
        user.setUserRole("admin");
        user.setGender(User.MALE);
        user.setCountry("Russian Federation");

        userDao.update(user);

        user = userDao.find(1);

        assertEquals("Russian Federation", user.getCountry());
    }

    @Test
    void updateWithInvalidParameter() {
        User user = new User();
        user.setId(1);
        user.setEmail("plizzz.healme@gmail.com");
        user.setUserRole("admin");
        user.setGender(User.MALE);
        user.setCountry(null);

        assertThrows(DaoException.class, () -> userDao.update(user));
    }

    @Test
    void updateNullUser() {
        assertThrows(DaoException.class, () -> userDao.update(null));
    }

    @Test
    void signInWithValidCredentials() throws DaoException {
        assertFalse(userDao.signIn("plizzz.healme@gmail.com", "1q2w3e").isNull());
    }

    @Test
    void signInWithInvalidCredentials() throws DaoException {
        assertTrue(userDao.signIn("plizzz.healme@gmail.com", "1q2w3e4r").isNull());
    }

    @Test
    void signInWithNullParameters() throws DaoException {
        assertTrue(userDao.signIn(null, null).isNull());
    }
}
