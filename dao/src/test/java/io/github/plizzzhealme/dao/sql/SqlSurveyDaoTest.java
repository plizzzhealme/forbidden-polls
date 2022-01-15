package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SqlSurveyDaoTest {

    @BeforeAll
    static void connect() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @AfterAll
    static void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }

    @Test
    void read() throws DaoException {
        SurveyDao surveyDao = DaoFactory.INSTANCE.getSurveyDao();

        Survey survey = surveyDao.read(1);
        System.out.println(survey);
        assertTrue(true);
    }
}