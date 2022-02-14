package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.exception.EntityNotFoundException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqlSurveyDaoTest {

    private final SurveyDao surveyDao = DaoFactory.INSTANCE.getSurveyDao();

    @BeforeAll
    static void connect() throws DaoException {
        ConnectionPool.INSTANCE.initPoolData();
    }

    @AfterAll
    static void disconnect() {
        ConnectionPool.INSTANCE.dispose();
    }

    @Test
    void findExistingSurvey() throws DaoException, EntityNotFoundException {
        int existingId = 1;
        Survey survey = surveyDao.find(existingId);
        System.out.println(survey);
        assertNotNull(survey);
    }

    @Test
    void findNonExistentSurvey() {
        int nonExistentId = -1;

        assertThrows(EntityNotFoundException.class, () -> surveyDao.find(nonExistentId));
    }

    @Test
    void criteriaSearchWithResult() throws DaoException {
        String existingSurveyName = "bad habits";
        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter(Parameter.SURVEY_NAME, existingSurveyName);

        assertFalse(surveyDao.search(criteria).isEmpty());
    }

    @Test
    void criteriaSearchWithoutResult() throws DaoException {
        String nonExistentSurveyName = "non-existent survey";
        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter(Parameter.SURVEY_NAME, nonExistentSurveyName);

        assertTrue(surveyDao.search(criteria).isEmpty());
    }

    @Test
    void nullCriteriaSearch() throws DaoException {
        assertTrue(surveyDao.search(null).isEmpty());
    }

    @Test
    void emptyCriteriaSearch() throws DaoException {
        assertFalse(surveyDao.search(new SearchCriteria()).isEmpty());
    }

    @Test
    void invalidParameterCriteriaSearch() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter(Parameter.USER_EMAIL, "mail@mail.ru");

        assertThrows(DaoException.class, () -> surveyDao.search(criteria));
    }


    @Test
    void createValidSurvey() {
        Survey survey = new Survey();
        survey.setName("test2");
        survey.setCategory("politics");


        int questionCount = 3;
        int optionCount = 2;

        List<Question> questions = new ArrayList<>(questionCount);

        for (int i = 0; i < questionCount; i++) {
            Question question = new Question();
            question.setIndex(i);
            question.setOptionType(Question.SELECT);
            question.setBody("test question " + i + "?");
            question.setDescription("test description " + i);
            questions.add(question);

            List<Option> options = new ArrayList<>(optionCount);

            for (int j = 0; j < optionCount; j++) {
                Option option = new Option();
                option.setIndex(j);
                option.setBody("test option " + i);

                options.add(option);
            }

            question.setOptions(options);
        }

        survey.setQuestions(questions);

        assertDoesNotThrow(() -> surveyDao.create(survey));
    }

    @Test
    void adminSearch() throws DaoException {
        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter(Parameter.SURVEY_NAME, "t");
        criteria.addParameter(Parameter.SURVEY_CATEGORY_ID, 2);

        assertFalse(surveyDao.search(criteria).isEmpty());
    }
}