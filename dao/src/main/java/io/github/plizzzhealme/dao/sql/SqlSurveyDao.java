package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.exception.EntityNotFoundException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.SqlParameter;
import io.github.plizzzhealme.dao.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlSurveyDao implements SurveyDao {

    private static final Logger logger = LogManager.getLogger(SqlSurveyDao.class);

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String SELECT_SURVEY_BY_ID_SQL = "" +
            "SELECT surveys.name, surveys.creation_date, surveys.description, " +
            "surveys.instructions, surveys.image_url, categories.name " +
            "FROM surveys " +
            "JOIN categories ON categories.id = surveys.category_id " +
            "WHERE surveys.id = ?";

    private static final String ADD_SURVEY_RESULT_SQL = "" +
            "INSERT INTO passed_surveys " +
            "(completion_date, survey_id, user_id) VALUES (?, ?, ?)";

    private static final String ADD_USER_ANSWERS_SQL = "" +
            "INSERT INTO picked_options " +
            "(answer_text, user_id, option_id) " +
            "VALUES (?, ?, ?)";

    private static final String CHECK_IF_SURVEY_PASSED_BY_USER_SQL = "" +
            "SELECT EXISTS(" +
            "SELECT id " +
            "FROM passed_surveys " +
            "WHERE passed_surveys.survey_id = ? " +
            "AND passed_surveys.user_id = ?)";

    private static final String SELECT_SURVEYS_PASSED_BY_USER_SQL = "" +
            "SELECT passed_surveys.survey_id " +
            "FROM passed_surveys " +
            "WHERE passed_surveys.user_id = ?";

    private static final String ADD_SURVEY_SQL = "" +
            "INSERT INTO surveys " +
            "(name, creation_date, description, instructions, image_url, category_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM categories WHERE categories.name = ?))";

    private static final String ADD_QUESTION_SQL = "" +
            "INSERT INTO questions " +
            "(index_number, body, image_url, description, survey_id, option_type_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM option_types WHERE option_types.type = ?))";

    private static final String ADD_OPTION_SQL = "" +
            "INSERT INTO options " +
            "(body, index_number, question_id) VALUES (?, ?, ?)";

    @Override
    public Survey find(int id) throws DaoException, EntityNotFoundException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_SURVEY_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Survey survey = new Survey();

                survey.setId(id);
                survey.setCreationDate(Util.toJavaTime(resultSet.getTimestamp(SqlParameter.SURVEYS_CREATION_DATE)));
                survey.setName(resultSet.getString(SqlParameter.SURVEYS_NAME));
                survey.setCategory(resultSet.getString(SqlParameter.CATEGORIES_NAME));
                survey.setDescription(resultSet.getString(SqlParameter.SURVEYS_DESCRIPTION));
                survey.setImageUrl(resultSet.getString(SqlParameter.SURVEYS_IMAGE_URL));
                survey.setInstructions(resultSet.getString(SqlParameter.SURVEYS_INSTRUCTIONS));

                return survey;
            }

            throw new EntityNotFoundException("Survey is not found");
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey by id from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Survey> search(SearchCriteria searchCriteria) throws DaoException {
        if (searchCriteria == null) {
            return Collections.emptyList();
        }

        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = Util.buildSearchSql(searchCriteria, SqlParameter.SURVEYS);

        try {
            preparedStatement = connection.prepareStatement(sql);
            Util.setSearchParameters(searchCriteria, preparedStatement);
            resultSet = preparedStatement.executeQuery();

            List<Survey> result = new ArrayList<>();

            while (resultSet.next()) {
                Survey survey = new Survey();

                survey.setId(resultSet.getInt(SqlParameter.SURVEYS_ID));
                survey.setName(resultSet.getString(SqlParameter.SURVEYS_NAME));
                survey.setCreationDate(Util.toJavaTime(resultSet.getTimestamp(SqlParameter.SURVEYS_CREATION_DATE)));
                survey.setDescription(resultSet.getString(SqlParameter.SURVEYS_DESCRIPTION));
                survey.setInstructions(resultSet.getString(SqlParameter.SURVEYS_INSTRUCTIONS));
                survey.setImageUrl(resultSet.getString(SqlParameter.SURVEYS_IMAGE_URL));

                result.add(survey);
            }

            return result;
        } catch (SQLException e) {
            throw new DaoException("Error searching surveys in database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void create(Survey survey) throws DaoException {
        Connection connection = pool.takeConnection();

        try {
            connection.setAutoCommit(false);
            insertSurvey(connection, survey);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DaoException("Failed to rollback transaction", ex);
            }

            throw new DaoException("Failed to create survey", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Failed to turn autocommit on.", e);
            }

            pool.closeConnection(connection);
        }
    }

    private void insertSurvey(Connection connection, Survey survey) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(ADD_SURVEY_SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, survey.getName());
            preparedStatement.setTimestamp(2, Util.toSqlTime(survey.getCreationDate()));
            preparedStatement.setString(3, survey.getDescription());
            preparedStatement.setString(4, survey.getInstructions());
            preparedStatement.setString(5, survey.getImageUrl());
            preparedStatement.setString(6, survey.getCategory());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            for (Question question : survey.getQuestions()) {
                insertQuestion(connection, question, resultSet.getInt(1));
            }
        } finally {
            pool.closeConnection(null, preparedStatement, resultSet);
        }
    }

    private void insertQuestion(Connection connection, Question question, int surveyId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(ADD_QUESTION_SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, question.getIndex());
            preparedStatement.setString(2, question.getBody());
            preparedStatement.setString(3, question.getImageUrl());
            preparedStatement.setString(4, question.getDescription());
            preparedStatement.setInt(5, surveyId);
            preparedStatement.setString(6, question.getOptionType());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            for (Option option : question.getOptions()) {
                insertOption(connection, option, resultSet.getInt(1));
            }
        } finally {
            pool.closeConnection(null, preparedStatement, resultSet);
        }
    }

    private void insertOption(Connection connection, Option option, int questionId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(ADD_OPTION_SQL);

            preparedStatement.setString(1, option.getBody());
            preparedStatement.setInt(2, option.getIndex());
            preparedStatement.setInt(3, questionId);

            preparedStatement.executeUpdate();
        } finally {
            pool.closeConnection(null, preparedStatement);
        }
    }

    @Override
    public void addSurveyResult(Survey survey, int userId) throws DaoException {
        Connection connection = pool.takeConnection();

        try {
            connection.setAutoCommit(false);

            addPassedSurvey(connection, survey, userId);

            for (Question question : survey.getQuestions()) {
                addAnswer(question, connection, userId);
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DaoException("Failed to rollback transaction.", e);
            }

            throw new DaoException("Failed to add survey result.", e);
        } finally {
            pool.closeConnection(connection);

            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Failed to turn autocommit on.", e);
            }
        }
    }

    @Override
    public boolean isSurveyPassedByUser(int surveyId, int userId) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(CHECK_IF_SURVEY_PASSED_BY_USER_SQL);
            preparedStatement.setInt(1, surveyId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new DaoException("Error while checking record existence.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Survey> searchSurveysPassedByUser(int userId) throws DaoException, EntityNotFoundException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_SURVEYS_PASSED_BY_USER_SQL);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            List<Survey> result = new ArrayList<>();

            while (resultSet.next()) {
                int surveyId = resultSet.getInt(1);
                Survey survey = find(surveyId);
                result.add(survey);
            }

            return result;
        } catch (SQLException e) {
            throw new DaoException("Error searching passed surveys.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }


    private void addPassedSurvey(Connection connection, Survey survey, int userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_SURVEY_RESULT_SQL)) {

            preparedStatement.setTimestamp(1, Util.toSqlTime(LocalDateTime.now()));
            preparedStatement.setInt(2, survey.getId());
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to add passed survey", e);
        }
    }

    private void addAnswer(Question question, Connection connection, int userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_ANSWERS_SQL)) {

            int answerIndex = question.getAnswerIndex();
            Option option = question.getOptions().get(answerIndex);
            preparedStatement.setString(1, option.getBody());
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, option.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to add answer.", e);
        }
    }
}
