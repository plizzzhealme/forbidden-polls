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
    void readExistentUserRole() throws DaoException {
        UserRoleDao userRoleDao = DaoFactory.INSTANCE.getUserRoleDao();

        int existentID = 1;
        String expected = "admin";
        String actual = userRoleDao.read(existentID);

        assertEquals(expected, actual);
    }

    @Test
    void readNonexistentUserRole() throws DaoException {
        UserRoleDao userRoleDao = DaoFactory.INSTANCE.getUserRoleDao();

        int nonexistentID = 5;
        String actual = userRoleDao.read(nonexistentID);

        assertNull(actual);
    }
}
