package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.dao.OptionDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlOptionDao implements OptionDao {

    public static final String OPTIONS_ID = "options.id";
    public static final String OPTIONS_INDEX_NUMBER = "options.index_number";
    public static final String OPTIONS_BODY = "options.body";
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_OPTIONS_BY_QUESTION_ID = "" +
            "SELECT options.id, options.index_number, options.body " +
            "FROM forbidden_polls.options " +
            "WHERE options.question_id = ? " +
            "ORDER BY options.index_number";

    @Override
    public List<Option> search(int questionId) throws DaoException {
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

                option.setId(resultSet.getInt(OPTIONS_ID));
                option.setIndex(resultSet.getInt(OPTIONS_INDEX_NUMBER));
                option.setBody(resultSet.getString(OPTIONS_BODY));

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
