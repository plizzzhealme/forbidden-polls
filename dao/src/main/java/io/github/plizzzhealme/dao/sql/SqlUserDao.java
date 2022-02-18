package io.github.plizzzhealme.dao.sql;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.dao.UserDao;
import io.github.plizzzhealme.dao.exception.DaoException;
import io.github.plizzzhealme.dao.exception.EntityNotFoundException;
import io.github.plizzzhealme.dao.exception.InvalidPasswordException;
import io.github.plizzzhealme.dao.pool.ConnectionPool;
import io.github.plizzzhealme.dao.util.SqlParameter;
import io.github.plizzzhealme.dao.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlUserDao implements UserDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    private static final String CHECK_IF_USER_EXISTS_SQL = "" +
            "SELECT EXISTS(SELECT 1 FROM users WHERE users.email = ?)";

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
            "(SELECT id FROM user_roles WHERE user_roles.name=? LIMIT 1), " +
            "(SELECT id FROM countries WHERE countries.iso_code = ? OR countries.name = ? LIMIT 1), " +
            "(SELECT id FROM genders WHERE genders.name=? LIMIT 1))";

    private static final String UPDATE_USER_SQL = "" +
            "UPDATE users " +
            "SET users.name=?, users.email=?, users.birthday=?,  " +
            "users.gender_id=(SELECT id FROM genders WHERE genders.name=? LIMIT 1), " +
            "users.country_id=(SELECT id FROM countries WHERE countries.iso_code = ? OR countries.name = ? LIMIT 1) " +
            "WHERE users.id=?";
    private static final String SELECT_USER_ROLE_BY_USER_ID = "" +
            "SELECT user_roles.name FROM forbidden_polls.user_roles " +
            "JOIN users ON user_roles.id = users.user_role_id " +
            "WHERE users.id = ?";

    private static final String BLOCK_USER_SQL = "" +
            "UPDATE users " +
            "SET users.user_role_id = (SELECT id FROM user_roles WHERE user_roles.name = ? LIMIT 1) " +
            "WHERE users.id = ?";

    private static void setSearchParameters(SearchCriteria criteria,
                                            PreparedStatement preparedStatement,
                                            int limit,
                                            int offset) throws SQLException {

        int i = 1;

        for (Parameter parameter : criteria.getSearchParameters().keySet()) {
            preparedStatement.setObject(i, "%" + criteria.getSearchParameters().get(parameter) + "%");
            i++;
        }

        preparedStatement.setInt(i++, limit);
        preparedStatement.setInt(i, offset);
    }

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
     * must not be null. Role, country and gender must
     * have correct values. Email must be unique.
     *
     * @param user object with user data
     * @throws DaoException         if the above requirements are violated
     *                              and in other cases of SQLException
     * @throws NullPointerException if user is null
     */
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
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getGender());

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
     * @return user object if a user with that id exists
     * @throws DaoException            in case of SQLException
     * @throws EntityNotFoundException if a user with that id does not exist
     */
    @Override
    public User find(int id) throws DaoException, EntityNotFoundException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();

                user.setId(id);
                user.setName(resultSet.getString(SqlParameter.USERS_NAME));
                user.setEmail(resultSet.getString(SqlParameter.USERS_EMAIL));
                user.setRegistrationDate(Util.toJavaTime(resultSet.getTimestamp(SqlParameter.USERS_REGISTRATION_DATE)));
                user.setBirthday(Util.toJavaTime(resultSet.getDate(SqlParameter.USERS_BIRTHDAY)));
                user.setUserRole(resultSet.getString(SqlParameter.USER_ROLES_NAME));
                user.setCountry(resultSet.getString(SqlParameter.COUNTRIES_NAME));
                user.setGender(resultSet.getString(SqlParameter.GENDERS_NAME));

                return user;
            }

            throw new EntityNotFoundException("User with id " + id + " is not found");
        } catch (SQLException e) {
            throw new DaoException("Error while reading user with id " + id + " data from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Updates user record the database. Searches user by id.
     * Email, role, country and gender must not be null.
     * Role, country and gender must have correct values.
     * Email must be unique.
     *
     * @param user object with user data
     * @throws DaoException if the above requirements are violated
     *                      and in other cases of SQLException
     * @throws NullPointerException if user is null
     */
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
            preparedStatement.setString(6, user.getCountry());
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("User update error.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public String readRole(int userId) throws DaoException, EntityNotFoundException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_ROLE_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(SqlParameter.USER_ROLES_NAME);
            }

            throw new EntityNotFoundException("User is not found");
        } catch (SQLException e) {
            throw new DaoException("Error while reading user data from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Authorizes the user. Reads user by email
     * and then compares password hashes.
     * Passing null parameters will produce NPE
     *
     * @param email    user email
     * @param password user password
     * @return user object if credentials are correct
     * @throws DaoException             in case of SQLException
     * @throws EntityNotFoundException  if the user with the specified email does not exist
     * @throws InvalidPasswordException if the password is wrong
     */
    @Override
    public User signIn(String email, String password)
            throws DaoException, EntityNotFoundException, InvalidPasswordException {

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

                if (isCorrectPassword) {
                    User user = new User();

                    user.setId(resultSet.getInt(SqlParameter.USERS_ID));
                    user.setUserRole(resultSet.getString(SqlParameter.USER_ROLES_NAME));

                    return user;
                }

                throw new InvalidPasswordException("Invalid password");
            }

            throw new EntityNotFoundException("User is not found");
        } catch (SQLException e) {
            throw new DaoException("Error while reading from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<User> search(SearchCriteria criteria, int limit, int offset) throws DaoException {
        Connection connection = pool.takeConnection();

        String sql = buildSearchQuery(criteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            setSearchParameters(criteria, preparedStatement, limit, offset);

            resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt(SqlParameter.USERS_ID));
                user.setName(resultSet.getString(SqlParameter.USERS_NAME));
                user.setEmail(resultSet.getString(SqlParameter.USERS_EMAIL));
                user.setRegistrationDate(Util.toJavaTime(resultSet.getTimestamp(SqlParameter.USERS_REGISTRATION_DATE)));
                user.setBirthday(Util.toJavaTime(resultSet.getDate(SqlParameter.USERS_BIRTHDAY)));
                user.setUserRole(resultSet.getString(SqlParameter.USER_ROLES_NAME));
                user.setCountry(resultSet.getString(SqlParameter.COUNTRIES_NAME));
                user.setGender(resultSet.getString(SqlParameter.GENDERS_NAME));

                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new DaoException("Error while reading user data from database.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void updateUserRole(String userRole, int userId) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(BLOCK_USER_SQL);

            preparedStatement.setString(1, userRole);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("User update error.", e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
    }

    private String buildSearchQuery(SearchCriteria criteria) {
        String queryBegin = "SELECT * FROM users " +
                "JOIN user_roles ON user_roles.id = users.user_role_id " +
                "JOIN countries ON countries.id = users.country_id " +
                "JOIN genders ON genders.id = users.gender_id ";
        String queryEnd = "ORDER BY users.id LIMIT ? OFFSET ?";

        if (criteria.getSearchParameters().isEmpty()) {
            return queryBegin + queryEnd;
        }

        return criteria.getSearchParameters().keySet()
                .stream()
                .map(parameter -> SqlParameter.getSqlParameter(parameter) + " LIKE ? ")
                .collect(Collectors.joining(" AND ", queryBegin + "WHERE ", queryEnd));
    }
}
