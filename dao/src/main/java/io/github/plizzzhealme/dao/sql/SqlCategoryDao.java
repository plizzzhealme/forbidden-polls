package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.Category;
import io.github.plizzzhealme.dao.CategoryDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlCategoryDao implements CategoryDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM forbidden_polls.categories";

    @Override
    public List<Category> findAll() throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getInt("categories.id"));
                category.setName(resultSet.getString("categories.name"));

                categories.add(category);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading categories data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return categories;
    }
}
