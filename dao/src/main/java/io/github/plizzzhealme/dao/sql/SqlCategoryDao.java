package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.dao.CategoryDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.SqlParameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlCategoryDao implements CategoryDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String CREATE_NEW_CATEGORY_SQL = "" +
            "INSERT INTO forbidden_polls.categories (name) VALUES (?)";

    private static final String SELECT_ALL_CATEGORIES_SQL = "" +
            "SELECT * FROM forbidden_polls.categories";

    private static final String CHECK_IF_CATEGORY_EXISTS_SQL = "" +
            "SELECT EXISTS(SELECT id FROM forbidden_polls.categories WHERE categories.name = ?)";

    private static final String SELECT_CATEGORY_ID_SQL = "" +
            "SELECT categories.id FROM forbidden_polls.categories WHERE categories.name = ?";

    @Override
    public List<String> findAll() throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<String> categories = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES_SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString(SqlParameter.CATEGORIES_NAME);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading categories data from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return categories;
    }

    @Override
    public int findId(String category) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_CATEGORY_ID_SQL);
            preparedStatement.setString(1, category);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getInt(SqlParameter.CATEGORIES_ID);
        } catch (SQLException e) {
            throw new DaoException("Error while reading category ID from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void create(String category) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(CREATE_NEW_CATEGORY_SQL);
            preparedStatement.setString(1, category);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Category creation error.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean isPresent(String category) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(CHECK_IF_CATEGORY_EXISTS_SQL);
            preparedStatement.setString(1, category);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new DaoException("Error while checking category existence.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
