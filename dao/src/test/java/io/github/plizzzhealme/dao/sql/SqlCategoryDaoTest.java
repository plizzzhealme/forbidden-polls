package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.dao.CategoryDao;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlCategoryDaoTest {

    @BeforeAll
    static void connect() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @AfterAll
    static void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }

    @Test
    void findAll() throws DaoException {
        CategoryDao categoryDao = DaoFactory.INSTANCE.getCategoryDao();

        List<String> actual = categoryDao.findAll();
        int expectedSize = 3;
        int actualSize = actual.size();

        assertEquals(expectedSize, actualSize);
    }
}