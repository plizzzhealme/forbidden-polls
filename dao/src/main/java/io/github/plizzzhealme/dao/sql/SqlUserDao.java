package io.github.plizzzhealme.dao.sql;

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

    private static final String USER_ID = "U.id";
    private static final String USER_EMAIL = "U.email";
    private static final String USER_NAME = "U.name";
    private static final String USER_BIRTHDAY = "U.birthday";
    private static final String USER_REGISTRATION_DATE = "U.registration_date";
    private static final String ROLE_NAME = "R.name";
    private static final String COUNTRY_NAME = "C.name";
    private static final String GENDER_NAME = "G.name";
    private static final String USER_PASSWORD_HASH = "U.hashed_password";

    private final ConnectionPool pool;

    public SqlUserDao() {
        pool = ConnectionPool.INSTANCE;
    }

    @Override
    public boolean create(User user, String password) throws DaoException {
        if (hasUserRecord(user.getEmail())) {
            return false;
        }

        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DaoUtil.CREATE_NEW_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, DaoUtil.hashPassword(password));
            preparedStatement.setTimestamp(4, DaoUtil.toSqlTime(user.getRegistrationDate()));
            preparedStatement.setDate(5, DaoUtil.toSqlTime(user.getBirthday()));
            preparedStatement.setString(6, user.getUserRole());
            preparedStatement.setString(7, user.getCountry());
            preparedStatement.setString(8, user.getGender());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Profile creation error.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public User read(int id) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(DaoUtil.SELECT_USER_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();

                user.setId(id);
                user.setName(resultSet.getString(USER_NAME));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setRegistrationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(USER_REGISTRATION_DATE)));
                user.setUserRole(resultSet.getString(ROLE_NAME));
                user.setCountry(resultSet.getString(COUNTRY_NAME));
                user.setGender(resultSet.getString(GENDER_NAME));
                user.setBirthday(DaoUtil.toJavaTime(resultSet.getDate(USER_BIRTHDAY)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading user data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public int authorize(String email, String password) throws DaoException {
        if (!hasUserRecord(email)) {
            return 0;
        }

        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DaoUtil.SELECT_USER_BY_EMAIL_SQL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean isCorrectPassword = DaoUtil.isCorrectPassword(password, resultSet.getString(USER_PASSWORD_HASH));

                if (!isCorrectPassword) {
                    return 0;
                } else {
                    return resultSet.getInt(USER_ID);
                }

            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public boolean hasUserRecord(String email) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DaoUtil.CHECK_IF_USER_EXISTS);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new DaoException("Error while checking record existence", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
