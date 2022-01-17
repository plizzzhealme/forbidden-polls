package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.Criteria;
import io.github.plizzzhealme.dao.SurveyDao;
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
    public static final String CATEGORIES_NAME = "categories.name";
    public static final String FORBIDDEN_POLLS_SURVEYS = "forbidden_polls.surveys";
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_SURVEY_BY_ID_SQL = "" +
            "SELECT surveys.name, surveys.creation_date, surveys.description, " +
            "surveys.instructions, surveys.image_url, categories.name " +
            "FROM forbidden_polls.surveys " +
            "JOIN forbidden_polls.categories ON categories.id = surveys.category_id " +
            "WHERE surveys.id = ?";

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
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading survey by id from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return survey;
    }

    @Override
    public List<Survey> search(Criteria criteria) throws DaoException {
        Connection connection = pool.takeConnection();

        List<Survey> result = new ArrayList<>();
        String sql = DaoUtil.buildSearchSql(criteria, FORBIDDEN_POLLS_SURVEYS);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            DaoUtil.setSearchParameters(criteria, preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Survey survey = new Survey();

                survey.setId(resultSet.getInt(SURVEYS_ID));
                survey.setName(resultSet.getString(SURVEYS_NAME));
                survey.setCreationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(SURVEYS_CREATION_DATE)));
                survey.setDescription(resultSet.getString(SURVEYS_DESCRIPTION));
                survey.setInstructions(resultSet.getString(SURVEYS_INSTRUCTIONS));
                survey.setImageUrl(resultSet.getString(SURVEYS_IMAGE_URL));

                result.add(survey);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while searching survey data in database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return result;
    }
}
