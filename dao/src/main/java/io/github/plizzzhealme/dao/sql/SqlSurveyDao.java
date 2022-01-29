package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.DaoUtil;
import io.github.plizzzhealme.dao.util.SqlParameter;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlSurveyDao implements SurveyDao {


    public static final String CATEGORIES_NAME = "categories.name";
    public static final String FORBIDDEN_POLLS_SURVEYS = "forbidden_polls.surveys";
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String SELECT_SURVEY_BY_ID_SQL = "" +
            "SELECT surveys.name, surveys.creation_date, surveys.description, " +
            "surveys.instructions, surveys.image_url, categories.name " +
            "FROM forbidden_polls.surveys " +
            "JOIN forbidden_polls.categories ON categories.id = surveys.category_id " +
            "WHERE surveys.id = ?";

    private static final String ADD_NEW_SURVEY_RESULT_SQL = "" +
            "INSERT INTO forbidden_polls.passed_surveys " +
            "(completion_date, survey_id, user_id) " +
            "VALUES (?, ?, ?)";

    private static final String ADD_USER_ANSWERS_SQL = "" +
            "INSERT INTO forbidden_polls.picked_options " +
            "(answer_text, user_id, option_id) " +
            "VALUES (?, ?, ?)";
    private static final String CHECK_IF_SURVEY_COMPLETED_BY_USER = "" +
            "SELECT EXISTS(" +
            "SELECT id " +
            "FROM forbidden_polls.passed_surveys " +
            "WHERE passed_surveys.survey_id = ? " +
            "AND passed_surveys.user_id = ?)";
    private static final String SELECT_COMPLETED_SQL = "" +
            "SELECT passed_surveys.survey_id " +
            "FROM passed_surveys " +
            "WHERE passed_surveys.user_id = ?";
    private static final String ADD_NEW_SURVEY_SQL = "" +
            "INSERT INTO forbidden_polls.surveys " +
            "(name, creation_date, description, instructions, image_url, category_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM forbidden_polls.categories WHERE categories.name = ?))";
    private static final String ADD_NEW_QUESTION_SQL = "" +
            "INSERT INTO forbidden_polls.questions " +
            "(index_number, body, image_url, description, survey_id, option_type_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM forbidden_polls.option_types WHERE option_types.type = ?))";
    private static final String ADD_NEW_OPTION_SQL = "" +
            "INSERT INTO forbidden_polls.options " +
            "(body, index_number, question_id) " +
            "VALUES (?, ?, ?)";


    @Override
    public Survey find(int id) throws DaoException {
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
                survey.setCreationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(SqlParameter.SURVEYS_CREATION_DATE)));
                survey.setName(resultSet.getString(SqlParameter.SURVEYS_NAME));
                survey.setCategory(resultSet.getString(CATEGORIES_NAME));
                survey.setDescription(resultSet.getString(SqlParameter.SURVEYS_DESCRIPTION));
                survey.setImageUrl(resultSet.getString(SqlParameter.SURVEYS_IMAGE_URL));
                survey.setInstructions(resultSet.getString(SqlParameter.SURVEYS_INSTRUCTIONS));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey by id from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return survey;
    }

    @Override
    public List<Survey> search(SearchCriteria searchCriteria) throws DaoException {
        Connection connection = pool.takeConnection();

        List<Survey> result = new ArrayList<>();
        String sql = DaoUtil.buildSearchSql(searchCriteria, FORBIDDEN_POLLS_SURVEYS);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            DaoUtil.setSearchParameters(searchCriteria, preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Survey survey = new Survey();

                survey.setId(resultSet.getInt(SqlParameter.SURVEYS_ID));
                survey.setName(resultSet.getString(SqlParameter.SURVEYS_NAME));
                survey.setCreationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(SqlParameter.SURVEYS_CREATION_DATE)));
                survey.setDescription(resultSet.getString(SqlParameter.SURVEYS_DESCRIPTION));
                survey.setInstructions(resultSet.getString(SqlParameter.SURVEYS_INSTRUCTIONS));
                survey.setImageUrl(resultSet.getString(SqlParameter.SURVEYS_IMAGE_URL));

                result.add(survey);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while searching survey data in database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return result;
    }

    @Override
    public void addSurveyResult(Survey survey, int userId) throws DaoException {
        Connection connection = pool.takeConnection();

        try {
            connection.setAutoCommit(false);

            addPassedSurvey(connection, survey, userId);
            addAnswers(userId, connection, survey.getQuestions());
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DaoException("", e);
            }
            throw new DaoException("Failed to add survey result", e);
        } finally {
            pool.closeConnection(connection);

            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                // todo log error
            }
        }
    }

    @Override
    public boolean isCompleted(int surveyId, int userId) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(CHECK_IF_SURVEY_COMPLETED_BY_USER);
            preparedStatement.setInt(1, surveyId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new DaoException("Error while checking record existence", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Survey> searchCompleted(int userId) throws DaoException {
        Connection connection = pool.takeConnection();
        List<Survey> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_COMPLETED_SQL);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int surveyId = resultSet.getInt(1);
                Survey survey = find(surveyId);
                result.add(survey);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey by id from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return result;
    }

    @Override
    public void create(Survey survey) throws DaoException {
        Connection connection = pool.takeConnection();

        try {
            connection.setAutoCommit(false);

            int surveyId = createSurvey(connection, survey);

            for (Question question : survey.getQuestions()) {
                int questionId = createQuestion(connection, surveyId, question);

                for (Option option : question.getOptions()) {
                    createOption(connection, questionId, option);
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DaoException("", e);
            } catch (SQLException ex) {
                throw new DaoException("", e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                //todo add log
            }

            pool.closeConnection(connection);
        }
    }

    private int createSurvey(Connection connection, Survey survey) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_SURVEY_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, survey.getName());
            preparedStatement.setTimestamp(2, DaoUtil.toSqlTime(survey.getCreationDate()));
            preparedStatement.setString(3, survey.getDescription());
            preparedStatement.setString(4, survey.getInstructions());
            preparedStatement.setString(5, survey.getImageUrl());
            preparedStatement.setString(6, survey.getCategory());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int surveyId = resultSet.getInt(1);
            resultSet.close();

            return surveyId;
        }
    }

    private int createQuestion(Connection connection, int surveyId, Question question) throws SQLException {
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(ADD_NEW_QUESTION_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, question.getIndex());
            preparedStatement.setString(2, question.getBody());
            preparedStatement.setString(3, question.getImageUrl());
            preparedStatement.setString(4, question.getDescription());
            preparedStatement.setInt(5, surveyId);
            preparedStatement.setString(6, question.getOptionType());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int questionId = resultSet.getInt(1);
            resultSet.close();

            return questionId;
        }
    }

    private void createOption(Connection connection, int questionId, Option option) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_OPTION_SQL)) {
            preparedStatement.setString(1, option.getBody());
            preparedStatement.setInt(2, option.getIndex());
            preparedStatement.setInt(3, questionId);

            preparedStatement.executeUpdate();
        }
    }

    private void addPassedSurvey(Connection connection, Survey survey, int userId) throws DaoException {
        int notAdded = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_SURVEY_RESULT_SQL)) {

            preparedStatement.setTimestamp(1, DaoUtil.toSqlTime(LocalDateTime.now()));
            preparedStatement.setInt(2, survey.getId());
            preparedStatement.setInt(3, userId);

            if (preparedStatement.executeUpdate() == notAdded) {
                throw new DaoException("Failed to add passed survey");
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to add passed survey", e);
        }

    }

    private void addAnswers(int userId, Connection connection, List<Question> questions) throws DaoException {
        for (Question question : questions) {
            addAnswer(question, connection, userId);
        }
    }

    private void addAnswer(Question question, Connection connection, int userId) throws DaoException {
        int notAdded = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_ANSWERS_SQL)) {
            int answerIndex = question.getAnswerIndex();
            Option option = question.getOptions().get(answerIndex);
            preparedStatement.setString(1, option.getBody());
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, option.getId());

            if (preparedStatement.executeUpdate() == notAdded) {
                throw new DaoException("Failed to add answer");
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to add answer", e);
        }
    }
}
