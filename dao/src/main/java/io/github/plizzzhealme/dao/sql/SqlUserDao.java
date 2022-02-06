package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.SqlParameter;
import io.github.plizzzhealme.dao.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDao implements UserDao {

    private static final String CHECK_IF_USER_EXISTS_SQL = "" +
            "SELECT EXISTS(SELECT id FROM forbidden_polls.users WHERE email = ?)";

    private static final String SELECT_USER_BY_EMAIL_SQL = "" +
            "SELECT * FROM forbidden_polls.users " +
            "JOIN forbidden_polls.user_roles ON users.user_role_id = user_roles.id " +
            "WHERE  users.email = ?";

    private static final String SELECT_USER_BY_ID_SQL = "" +
            "SELECT users.name, users.email, users.registration_date, users.birthday, " +
            "user_roles.name, countries.name, genders.name " +
            "FROM forbidden_polls.users " +
            "JOIN forbidden_polls.user_roles ON users.user_role_id = user_roles.id " +
            "JOIN forbidden_polls.countries ON users.country_id = countries.id " +
            "JOIN forbidden_polls.genders ON users.gender_id = genders.id " +
            "WHERE  users.id = ?";

    private static final String CREATE_USER_SQL = "" +
            "INSERT INTO forbidden_polls.users " +
            "(name, email, hashed_password, registration_date, birthday, user_role_id, country_id, gender_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM forbidden_polls.user_roles WHERE name=?), " +
            "(SELECT id FROM forbidden_polls.countries WHERE countries.iso_code=?), " +
            "(SELECT id FROM forbidden_polls.genders WHERE name=?))";

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String UPDATE_USER_SQL = "" +
            "UPDATE forbidden_polls.users " +
            "SET users.name=?, users.email=?, users.birthday=?,  " +
            "users.gender_id=(SELECT id FROM forbidden_polls.genders WHERE genders.name=?), " +
            "users.country_id=(SELECT id FROM forbidden_polls.countries WHERE countries.iso_code=?) " +
            "WHERE users.id=?";

    @Override
    public void create(User user) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(CREATE_USER_SQL);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, Util.hashPassword(user.getPassword()));
            preparedStatement.setTimestamp(4, Util.toSqlTime(user.getRegistrationDate()));
            preparedStatement.setDate(5, Util.toSqlTime(user.getBirthday()));
            preparedStatement.setString(6, user.getUserRole());
            preparedStatement.setString(7, user.getCountry());
            preparedStatement.setString(8, user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("User creation error.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setDate(3, Util.toSqlTime(user.getBirthday()));
            preparedStatement.setString(4, user.getGender());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("User update error.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public User find(int id) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = new User();

                user.setId(id);
                user.setName(resultSet.getString(SqlParameter.USERS_NAME));
                user.setEmail(resultSet.getString(SqlParameter.USERS_EMAIL));
                user.setRegistrationDate(Util.toJavaTime(resultSet.getTimestamp(SqlParameter.USERS_REGISTRATION_DATE)));
                user.setBirthday(Util.toJavaTime(resultSet.getDate(SqlParameter.USERS_BIRTHDAY)));
                user.setUserRole(resultSet.getString(SqlParameter.USER_ROLES_NAME));
                user.setCountry(resultSet.getString(SqlParameter.COUNTRIES_NAME));
                user.setGender(resultSet.getString(SqlParameter.GENDERS_NAME));
            }

            return user;
        } catch (SQLException e) {
            throw new DaoException("Error while reading user data from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public User signIn(String email, String password) throws DaoException {
        if (!isPresent(email)) {
            return null;
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
                boolean isCorrectPassword = Util.isCorrectPassword(password, hashedPassword);

                if (!isCorrectPassword) {
                    return null;
                } else {
                    User user = new User();
                    user.setId(resultSet.getInt(SqlParameter.USERS_ID));
                    user.setUserRole(resultSet.getString(SqlParameter.USER_ROLES_NAME));
                    return user;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    public List<User> search(SearchCriteria searchCriteria) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = Util.buildSearchSql(searchCriteria, SqlParameter.USERS);

            preparedStatement = connection.prepareStatement(sql);
            Util.setSearchParameters(searchCriteria, preparedStatement);
            resultSet = preparedStatement.executeQuery();

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt(SqlParameter.USERS_ID));
                user.setName(resultSet.getString(SqlParameter.USERS_NAME));
                user.setEmail(resultSet.getString(SqlParameter.USERS_EMAIL));
                user.setRegistrationDate(Util.toJavaTime(resultSet.getTimestamp(SqlParameter.USERS_REGISTRATION_DATE)));
                user.setBirthday(Util.toJavaTime(resultSet.getDate(SqlParameter.USERS_BIRTHDAY)));

                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new DaoException("Error while searching survey data in database", e);
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
            preparedStatement = connection.prepareStatement(CHECK_IF_USER_EXISTS_SQL);
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
