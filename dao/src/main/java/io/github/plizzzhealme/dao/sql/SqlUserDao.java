package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.SearchCriteria;
import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.DaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUserDao implements UserDao {

    private static final ConnectionPool connectionPool;

    static {
        connectionPool = ConnectionPool.INSTANCE;

        try {
            connectionPool.initPoolData();
        } catch (DaoException e) {
            // todo log error
        }
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public User read(int id) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String sql = "SELECT U.id, U.name, U.email, U.registration_date, U.phone_number, U.last_login , R.name, C.name, G.name " +
                "FROM survinator.users AS U " +
                "JOIN user_roles AS R on U.user_role_id = R.id " +
                "JOIN countries AS C on U.country_id = C.id " +
                "JOIN gender AS G on U.gender_id = G.id " +
                "WHERE  U.id = ?";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    user = new User();

                    user.setId(id);
                    user.setName(resultSet.getString("U.name"));
                    user.setEmail(resultSet.getString("U.email"));
                    user.setRegistrationDate(DaoUtil.toLocalDateTime(resultSet.getTimestamp("U.registration_date")));
                    user.setPhoneNumber(resultSet.getLong("U.phone_number"));
                    user.setLastLoginDate(DaoUtil.toLocalDateTime(resultSet.getTimestamp("U.last_login")));
                    user.setUserRole(resultSet.getString("R.name"));
                    user.setCountry(resultSet.getString("C.name"));
                    user.setGender(resultSet.getString("G.name"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading from database", e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }
            connectionPool.dispose();
        }

        return user;
    }

    @Override
    public boolean update(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }

    @Override
    public User search (SearchCriteria criteria) throws DaoException { // at the moment is just a stub to search by email
        Connection connection = connectionPool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String email = criteria.getParameters().get("email");

        String sql = "SELECT U.id, U.name, U.email, U.password_hash, U.registration_date, U.phone_number, U.last_login , R.name, C.name, G.name " +
                "FROM survinator.users AS U " +
                "JOIN user_roles AS R on U.user_role_id = R.id " +
                "JOIN countries AS C on U.country_id = C.id " +
                "JOIN gender AS G on U.gender_id = G.id " +
                "WHERE  U.email = ?";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();


                while (resultSet.next()) {
                    user = new User();

                    user.setId(resultSet.getInt("U.id"));
                    user.setName(resultSet.getString("U.name"));
                    user.setEmail(email);
                    user.setPasswordHash(resultSet.getInt("U.password_hash"));
                    user.setRegistrationDate(DaoUtil.toLocalDateTime(resultSet.getTimestamp("U.registration_date")));
                    user.setPhoneNumber(resultSet.getLong("U.phone_number"));
                    user.setLastLoginDate(DaoUtil.toLocalDateTime(resultSet.getTimestamp("U.last_login")));
                    user.setUserRole(resultSet.getString("R.name"));
                    user.setCountry(resultSet.getString("C.name"));
                    user.setGender(resultSet.getString("G.name"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading from database", e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, preparedStatement, resultSet);
            }
            connectionPool.dispose();
        }

        return user;
    }
}
