package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.dao.UserRoleDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUserRoleDao implements UserRoleDao {

    public static final String SELECT_BY_ID = "SELECT name FROM forbidden_polls.user_roles WHERE id = ?";

    @Override
    public String read(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.INSTANCE;

        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String userRole = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userRole = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading user data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return userRole;
    }

    @Override
    public int search(String userRoleName) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;

        String sql = "SELECT id FROM forbidden_polls.user_roles WHERE name = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userRoleName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading user data from database", e);
        } finally {
            ConnectionPool.INSTANCE.closeConnection(connection, preparedStatement, resultSet);
        }

        return id;
    }
}
