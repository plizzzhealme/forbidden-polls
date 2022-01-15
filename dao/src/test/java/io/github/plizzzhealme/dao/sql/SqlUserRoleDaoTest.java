package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserRoleDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SqlUserRoleDaoTest {

    @BeforeAll
    static void start() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @AfterAll
    static void finish() {
        ConnectionPool.INSTANCE.dispose();
    }

    @Test
    void readWithExistingID() throws DaoException {
        UserRoleDao userRoleDao = DaoFactory.INSTANCE.getUserRoleDao();

        int existingID = 1;
        String expected = "admin";
        String actual = userRoleDao.read(existingID);

        assertEquals(expected, actual);
    }

    @Test
    void readWithNonExistentID() throws DaoException {
        UserRoleDao userRoleDao = DaoFactory.INSTANCE.getUserRoleDao();

        int nonExistentID = 5;
        String actual = userRoleDao.read(nonExistentID);

        assertNull(actual);
    }

    @Test
    void readWithExistingUserRole() throws DaoException {
        UserRoleDao userRoleDao = DaoFactory.INSTANCE.getUserRoleDao();

        String existingRole = "admin";
        int expected = 1;
        int actual = userRoleDao.search(existingRole);

        assertEquals(expected, actual);
    }

    @Test
    void readWithNonExistentUserRole() throws DaoException {
        UserRoleDao userRoleDao = DaoFactory.INSTANCE.getUserRoleDao();

        String nonExistentUserRole = "god";
        int expected = 0;
        int actual = userRoleDao.search(nonExistentUserRole);

        assertEquals(expected, actual);
    }
}
