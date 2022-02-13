package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.exception.EntityNotFoundException;
import io.github.plizzzhealme.dao.exception.InvalidPasswordException;
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
        user.setGender(User.OTHER_GENDER);
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
        user.setGender(User.MALE_GENDER);

        assertThrows(DaoException.class, () -> userDao.create(user));
    }

    @Test
    void findExistingUser() throws DaoException, EntityNotFoundException {
        assertNotNull(userDao.find(1));
    }

    @Test
    void findNonExistentUser() {
        assertThrows(EntityNotFoundException.class, () -> userDao.find(-1));
    }

    @Test
    void correctUpdate() throws DaoException, EntityNotFoundException {
        User user = new User();
        user.setId(1);
        user.setEmail("plizzz.healme@gmail.com");
        user.setUserRole("admin");
        user.setGender(User.MALE_GENDER);
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
        user.setGender(User.MALE_GENDER);
        user.setCountry(null);

        assertThrows(DaoException.class, () -> userDao.update(user));
    }

    @Test
    void signInWithValidCredentials() throws DaoException, InvalidPasswordException, EntityNotFoundException {
        assertNotNull(userDao.signIn("plizzz.healme@gmail.com", "1q2w3e"));
    }

    @Test
    void signInWithInvalidCredentials() {
        assertThrows(InvalidPasswordException.class,
                () -> userDao.signIn("plizzz.healme@gmail.com", "1q2w3e4r"));
    }

    @Test
    void signInWithNullParameters() {
        assertThrows(EntityNotFoundException.class, () -> userDao.signIn(null, null));
    }
}
