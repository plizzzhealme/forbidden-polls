package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.SearchCriteria;
import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.exception.DaoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlUserDaoTest {

    @Test
    void read() {
    }

    @Test
    void search() {
        String email = "plizzz.healme@gmail.com";
        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter("email", email);

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
            actual = DaoFactory.getUserDao().search(criteria);
        } catch (DaoException e) {
            actual = null;
        }

        assertEquals(expected, actual);
    }
}