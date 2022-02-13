package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.dao.OptionDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.SqlParameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlOptionDao implements OptionDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String SELECT_OPTIONS_BY_QUESTION_ID = "" +
            "SELECT * " +
            "FROM options " +
            "WHERE options.question_id = ? " +
            "ORDER BY options.index_number";

    private static final String COUNT_ANSWERS_SQL = "" +
            "SELECT COUNT(*) FROM picked_options WHERE picked_options.option_id = ?";

    @Override
    public int countAnswers(int optionId) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(COUNT_ANSWERS_SQL);
            preparedStatement.setInt(1, optionId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Error while reading options from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Option> searchQuestionOptions(int questionId) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Option> options = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_OPTIONS_BY_QUESTION_ID);
            preparedStatement.setInt(1, questionId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Option option = new Option();

                option.setId(resultSet.getInt(SqlParameter.OPTIONS_ID));
                option.setIndex(resultSet.getInt(SqlParameter.OPTIONS_INDEX_NUMBER));
                option.setBody(resultSet.getString(SqlParameter.OPTIONS_BODY));

                options.add(option);
            }

            return options;
        } catch (SQLException e) {
            throw new DaoException("Error while reading options from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
