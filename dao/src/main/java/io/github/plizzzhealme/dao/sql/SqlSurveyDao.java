package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.SurveyDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.DaoUtil;
import io.github.plizzzhealme.dao.util.SqlParameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
