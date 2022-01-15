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

    public static final String CHECK_IF_USER_EXISTS = "" +
            "SELECT EXISTS(SELECT id FROM forbidden_polls.users WHERE email = ?)";

    public static final String SELECT_USER_BY_EMAIL_SQL = "" +
            "SELECT U.id, U.hashed_password FROM forbidden_polls.users AS U WHERE  U.email = ?";

    public static final String SELECT_USER_BY_ID_SQL = "" +
            "SELECT U.name, U.email, U.registration_date, U.birthday, R.name, C.name, G.name " +
            "FROM forbidden_polls.users AS U " +
            "JOIN forbidden_polls.user_roles AS R ON U.user_role_id = R.id " +
            "JOIN forbidden_polls.countries AS C ON U.country_id = C.id " +
            "JOIN forbidden_polls.genders AS G ON U.gender_id = G.id " +
            "WHERE  U.id = ?";

    public static final String CREATE_NEW_USER_SQL = "" +
            "INSERT INTO forbidden_polls.users " +
            "(name, email, hashed_password, registration_date, birthday, user_role_id, country_id, gender_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM forbidden_polls.user_roles WHERE name=?), " +
            "(SELECT id FROM forbidden_polls.countries WHERE countries.iso_code=?), " +
            "(SELECT id FROM forbidden_polls.genders WHERE name=?))";

    private final ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public boolean create(User user, String password) throws DaoException {
        if (isPresent(user.getEmail())) {
            return false;
        }

        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(CREATE_NEW_USER_SQL);
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
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL);
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
        if (!isPresent(email)) {
            return 0;
        }

        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL);
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
    public boolean isPresent(String email) throws DaoException {
        Connection connection = pool.takeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(CHECK_IF_USER_EXISTS);
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
