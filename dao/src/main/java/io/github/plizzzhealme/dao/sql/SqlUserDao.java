package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.UserDao;
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

public class SqlUserDao implements UserDao {

    private static final String ROLE_NAME = "user_roles.name";
    private static final String COUNTRY_NAME = "countries.name";
    private static final String GENDER_NAME = "genders.name";

    private static final String CHECK_IF_USER_EXISTS = "" +
            "SELECT EXISTS(SELECT id FROM forbidden_polls.users WHERE email = ?)";
    private static final String SELECT_USER_BY_EMAIL_SQL = "" +
            "SELECT users.id, users.hashed_password FROM forbidden_polls.users WHERE  users.email = ?";
    private static final String SELECT_USER_BY_ID_SQL = "" +
            "SELECT users.name, users.email, users.registration_date, users.birthday, " +
            "user_roles.name, countries.name, genders.name " +
            "FROM forbidden_polls.users " +
            "JOIN forbidden_polls.user_roles ON users.user_role_id = user_roles.id " +
            "JOIN forbidden_polls.countries ON users.country_id = countries.id " +
            "JOIN forbidden_polls.genders ON users.gender_id = genders.id " +
            "WHERE  users.id = ?";
    private static final String CREATE_NEW_USER_SQL = "" +
            "INSERT INTO forbidden_polls.users " +
            "(name, email, hashed_password, registration_date, birthday, user_role_id, country_id, gender_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM forbidden_polls.user_roles WHERE name=?), " +
            "(SELECT id FROM forbidden_polls.countries WHERE countries.iso_code=?), " +
            "(SELECT id FROM forbidden_polls.genders WHERE name=?))";

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;


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
    public User find(int id) throws DaoException {
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
                user.setName(resultSet.getString(SqlParameter.USERS_NAME));
                user.setEmail(resultSet.getString(SqlParameter.USERS_EMAIL));
                user.setRegistrationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(SqlParameter.USERS_REGISTRATION_DATE)));
                user.setBirthday(DaoUtil.toJavaTime(resultSet.getDate(SqlParameter.USERS_BIRTHDAY)));
                user.setUserRole(resultSet.getString(ROLE_NAME));
                user.setCountry(resultSet.getString(COUNTRY_NAME));
                user.setGender(resultSet.getString(GENDER_NAME));

            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading user data from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public int signIn(String email, String password) throws DaoException {
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
                String hashedPassword = resultSet.getString(SqlParameter.USERS_HASHED_PASSWORD);
                boolean isCorrectPassword = DaoUtil.isCorrectPassword(password, hashedPassword);

                if (!isCorrectPassword) {
                    return 0;
                } else {
                    return resultSet.getInt(SqlParameter.USERS_ID);
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

    public List<User> search(SearchCriteria searchCriteria) throws DaoException {
        Connection connection = pool.takeConnection();

        List<User> result = new ArrayList<>();

        String sql = DaoUtil.buildSearchSql(searchCriteria, SqlParameter.USERS);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            DaoUtil.setSearchParameters(searchCriteria, preparedStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt(SqlParameter.USERS_ID));
                user.setName(resultSet.getString(SqlParameter.USERS_NAME));
                user.setEmail(resultSet.getString(SqlParameter.USERS_EMAIL));
                user.setRegistrationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(SqlParameter.USERS_REGISTRATION_DATE)));
                user.setBirthday(DaoUtil.toJavaTime(resultSet.getDate(SqlParameter.USERS_BIRTHDAY)));

                result.add(user);
            }


        } catch (SQLException e) {
            throw new DaoException("Error while searching survey data in database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }

        return result;
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
