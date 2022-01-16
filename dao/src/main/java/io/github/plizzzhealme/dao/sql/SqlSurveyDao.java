package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.criteria.Criteria;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.DaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlSurveyDao implements SurveyDao {

    public static final String SURVEYS_ID = "surveys.id";
    public static final String SURVEYS_NAME = "surveys.name";
    public static final String SURVEYS_IMAGE_URL = "surveys.image_url";
    public static final String SURVEYS_INSTRUCTIONS = "surveys.instructions";
    public static final String SURVEYS_CREATION_DATE = "surveys.creation_date";
    public static final String SURVEYS_DESCRIPTION = "surveys.description";

    public static final String OPTION_TYPES_TYPE = "option_types.type";

    public static final String CATEGORIES_NAME = "categories.name";

    public static final String QUESTIONS_ID = "questions.id";
    public static final String QUESTIONS_BODY = "questions.body";
    public static final String QUESTIONS_INDEX_NUMBER = "questions.index_number";

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String SELECT_SURVEY_BY_ID_SQL = "" +
            "SELECT surveys.name, surveys.creation_date, surveys.description, " +
            "surveys.instructions, surveys.image_url, categories.name " +
            "FROM forbidden_polls.surveys " +
            "JOIN forbidden_polls.categories ON categories.id = surveys.category_id " +
            "WHERE surveys.id = ?";

    private static final String SELECT_QUESTIONS_BY_SURVEY_ID = "" +
            "SELECT questions.id, questions.index_number, questions.body, questions.image_url, " +
            "questions.question_description, option_types.type " +
            "FROM forbidden_polls.questions " +
            "JOIN forbidden_polls.option_types ON option_types.id = questions.option_type_id " +
            "WHERE questions.survey_id = ? " +
            "ORDER BY questions.index_number";

    private static final String SELECT_OPTIONS_BY_QUESTION_ID = "" +
            "SELECT options.id, options.index_number, options.body " +
            "FROM forbidden_polls.options " +
            "WHERE options.question_id = ? " +
            "ORDER BY options.index_number";

    private static final String SELECT_SURVEY_BY_NAME_SQL = "" +
            "SELECT surveys.id " +
            "FROM forbidden_polls.surveys " +
            "WHERE surveys.name = ?";


    @Override
    public Survey read(int id) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Survey survey = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_SURVEY_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                survey = new Survey();
                survey.setId(id);
                survey.setCreationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(SURVEYS_CREATION_DATE)));
                survey.setName(resultSet.getString(SURVEYS_NAME));
                survey.setCategory(resultSet.getString(CATEGORIES_NAME));
                survey.setDescription(resultSet.getString(SURVEYS_DESCRIPTION));
                survey.setImageUrl(resultSet.getString(SURVEYS_IMAGE_URL));
                survey.setInstructions(resultSet.getString(SURVEYS_INSTRUCTIONS));

                survey.setQuestions(readQuestions(id));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }


        return survey;
    }

    @Override
    public int search(String surveyName) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = 0;

        try {
            preparedStatement = connection.prepareStatement(SELECT_SURVEY_BY_NAME_SQL);
            preparedStatement.setString(1, surveyName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(SURVEYS_ID);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return id;
    }

    @Override
    public List<Integer> search(Criteria criteria) throws DaoException {
        Connection connection = pool.takeConnection();

        List<Integer> result = new ArrayList<>();
        String sql = DaoUtil.buildSearchSql(criteria, "forbidden_polls.surveys");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            DaoUtil.setSearchParameters(criteria, preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(resultSet.getInt(SURVEYS_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return result;
    }


    private List<Question> readQuestions(int surveyID) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_QUESTIONS_BY_SURVEY_ID);
            preparedStatement.setInt(1, surveyID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question();

                int questionID = resultSet.getInt(QUESTIONS_ID);
                question.setId(questionID);
                question.setBody(resultSet.getString(QUESTIONS_BODY));
                question.setIndex(resultSet.getInt(QUESTIONS_INDEX_NUMBER));
                question.setOptionType(resultSet.getString(OPTION_TYPES_TYPE));
                question.setOptions(readOptions(questionID));

                questions.add(question);

            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading question data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return questions;
    }

    private List<Option> readOptions(int questionID) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Option> options = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_OPTIONS_BY_QUESTION_ID);
            preparedStatement.setInt(1, questionID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Option option = new Option();

                option.setId(resultSet.getInt("id"));
                option.setIndex(resultSet.getInt("index_number"));
                option.setBody(resultSet.getString("body"));

                options.add(option);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading option data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return options;
    }
}
