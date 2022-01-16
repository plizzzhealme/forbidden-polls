package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.criteria.Column;
import io.github.plizzzhealme.dao.criteria.Criteria;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        String expected = "Poll about smoking";
        String actual = survey.getName();

        assertEquals(expected, actual);
    }

    @Test
    void testSearch() throws DaoException {
        SurveyDao surveyDao = DaoFactory.INSTANCE.getSurveyDao();

        Criteria criteria = new Criteria();
        criteria.addParameter(Column.SURVEYS_NAME, "Poll about smoking");
        List<Integer> expected = List.of(1);
        List<Integer> actual = surveyDao.search(criteria);

        assertEquals(expected, actual);
    }
}