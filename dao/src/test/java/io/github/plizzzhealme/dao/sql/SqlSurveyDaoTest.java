package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.DaoFactory;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

        Survey survey = surveyDao.find(1);

        String expected = "Poll about smoking";
        String actual = survey.getName();

        assertEquals(expected, actual);
    }

    @Test
    void testCriteriaSearch() throws DaoException {
        SurveyDao surveyDao = DaoFactory.INSTANCE.getSurveyDao();

        SearchCriteria searchCriteria = new SearchCriteria();
        String expectedName = "Poll about smoking";
        searchCriteria.addParameter(Parameter.SURVEY_NAME, expectedName);
        List<Survey> surveys = surveyDao.search(searchCriteria);
        String actualName = surveys.get(0).getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void searchCompleted() throws DaoException {
        SurveyDao surveyDao = DaoFactory.INSTANCE.getSurveyDao();

        List<Survey> completedSurveys = surveyDao.searchSurveysPassedByUser(1);
        System.out.println(completedSurveys);
    }

    @Test
    void create() throws DaoException {
        DaoFactory daoFactory = DaoFactory.INSTANCE;
        SurveyDao surveyDao = daoFactory.getSurveyDao();

        Survey survey = new Survey();
        survey.setName("Test survey");
        survey.setCategory("politics");

        List<Question> questions = new ArrayList<>();

        Question question1 = new Question();
        question1.setBody("Test question 1?");
        question1.setOptionType("select");
        question1.setIndex(1);
        List<Option> options1 = new ArrayList<>();
        Option option11 = new Option();
        option11.setIndex(1);
        option11.setBody("Option 1 for question 1");

        Option option21 = new Option();
        option21.setIndex(2);
        option21.setBody("Option 2 for question 1");
        options1.add(option11);
        options1.add(option21);

        question1.setOptions(options1);


        Question question2 = new Question();
        question2.setBody("Test question 2?");
        question2.setOptionType("select");
        question2.setIndex(2);
        List<Option> options2 = new ArrayList<>();
        Option option12 = new Option();
        option12.setIndex(1);
        option12.setBody("Option 1 for question 2");

        Option option22 = new Option();
        option22.setIndex(2);
        option22.setBody("Option 2 for question 2");
        options2.add(option12);
        options2.add(option22);

        question2.setOptions(options2);

        questions.add(question1);
        questions.add(question2);

        survey.setQuestions(questions);

        surveyDao.create(survey);

        System.out.println(survey);


    }

}