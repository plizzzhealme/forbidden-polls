package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlUserDaoTest {

    @BeforeAll
    static void connect() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @AfterAll
    static void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }

    @Test
    void readWithExistingID() throws DaoException {
        UserDao userDao = DaoFactory.INSTANCE.getUserDao();

        int existingID = 1;
        String expected = "plizzz.healme@gmail.com";
        String actual = userDao.find(existingID).getEmail();

        assertEquals(expected, actual);
    }

    @Test
    void create() throws DaoException {
        UserDao dao = DaoFactory.INSTANCE.getUserDao();

        User user = new User();
        user.setPassword("1q2w3e");
        user.setEmail("plizzzehesalme@gmail.com");
        user.setName("Dzianis");
        user.setCountry("Belarus");
        user.setGender("male");
        user.setUserRole("admin");

        dao.create(user);
    }


    @Test
    void testSearch() throws DaoException {

        UserDao userDao = DaoFactory.INSTANCE.getUserDao();
        LocalDate date = LocalDate.of(1989, 9, 5);

        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter(Parameter.USER_BIRTHDAY, date);
        List<User> users = userDao.search(criteria);

        assertEquals(date, users.get(0).getBirthday());
    }

}
