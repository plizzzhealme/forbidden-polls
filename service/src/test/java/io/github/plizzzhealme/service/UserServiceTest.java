package io.github.plizzzhealme.service;

import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

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


}