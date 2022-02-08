package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.SqlParameter;
import io.github.plizzzhealme.dao.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUserDao implements UserDao {

    private static final String CHECK_IF_USER_EXISTS_SQL = "" +
            "SELECT EXISTS(SELECT id FROM users WHERE email = ?)";

    private static final String SELECT_USER_BY_EMAIL_SQL = "" +
            "SELECT * FROM users " +
            "JOIN user_roles ON users.user_role_id = user_roles.id " +
            "WHERE  users.email = ?";

    private static final String SELECT_USER_BY_ID_SQL = "" +
            "SELECT users.name, users.email, users.registration_date, users.birthday, " +
            "user_roles.name, countries.name, genders.name " +
            "FROM users " +
            "JOIN user_roles ON users.user_role_id = user_roles.id " +
            "JOIN countries ON users.country_id = countries.id " +
            "JOIN genders ON users.gender_id = genders.id " +
            "WHERE  users.id = ?";

    private static final String CREATE_USER_SQL = "" +
            "INSERT INTO users " +
            "(name, email, hashed_password, registration_date, birthday, user_role_id, country_id, gender_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM user_roles WHERE name=? LIMIT 1), " +
            "(SELECT id FROM countries WHERE countries.iso_code OR countries.name = ? LIMIT 1), " +
            "(SELECT id FROM genders WHERE name=? LIMIT 1))";

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String UPDATE_USER_SQL = "" +
            "UPDATE users " +
            "SET users.name=?, users.email=?, users.birthday=?,  " +
            "users.gender_id=(SELECT id FROM genders WHERE genders.name=?), " +
            "users.country_id=(SELECT id FROM countries WHERE countries.iso_code OR countries.name = ?) " +
            "WHERE users.id=?";

    /**
     * Checks if the user exists in the database
     *
     * @param email user email
     * @return true if exists
     * @throws DaoException in a case of SQLException
     */
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
            throw new DaoException("Error while checking user record existence.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Creates a record with user data in the database.
     * User, email, password, role, country and gender
     * should not be null. Role, country and gender should
     * have correct values. Email should be unique.
     *
     * @param user object with user data
     * @throws DaoException if the above requirements are violated
     *                      and in other cases of SQLException
     */
    @Override
    public void create(User user) throws DaoException {
        if (user == null) {
            throw new DaoException("Cannot create null user record.");
        }

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

    /**
     * Searches user by id
     *
     * @param id user id
     * @return user object if found, null if not
     * @throws DaoException in a case of SQLException
     */
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

    /**
     * Updates user record the database. Searches user by id.
     * User, email, role, country and gender should not be null.
     * Role, country and gender should have correct values.
     * Email should be unique.
     *
     * @param user object with user data
     * @throws DaoException if the above requirements are violated
     *                      and in other cases of SQLException
     */
    @Override
    public void update(User user) throws DaoException {
        if (user == null) {
            throw new DaoException("Cannot update null record");
        }
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

    /**
     * Authorizes the user. Reads user by email
     * and then compares password hashes
     *
     * @param email    user email
     * @param password user password
     * @return user object if credentials a valid, null if not
     * @throws DaoException in case of SQLException
     */
    @Override
    public User signIn(String email, String password) throws DaoException {
        if (email == null || password == null) {
            return null;
        }

        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString(SqlParameter.USERS_HASHED_PASSWORD);
                boolean isCorrectPassword = Util.isCorrectPassword(password, hashedPassword);

                if (isCorrectPassword) {
                    user = new User();

                    user.setId(resultSet.getInt(SqlParameter.USERS_ID));
                    user.setUserRole(resultSet.getString(SqlParameter.USER_ROLES_NAME));
                }
            }

            return user;
        } catch (SQLException e) {
            throw new DaoException("Error while reading from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
