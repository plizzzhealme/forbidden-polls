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

    private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT U.id, U.name, U.password_hash, U.registration_date, U.phone_number, U.last_login , R.name, C.name, G.name FROM survinator.users AS U JOIN user_roles AS R on U.user_role_id = R.id JOIN countries AS C on U.country_id = C.id JOIN gender AS G on U.gender_id = G.id WHERE  U.email = ?";
    private static final String USER_ID = "U.id";
    private static final String USER_NAME = "U.name";
    private static final String USER_PASSWORD_HASH = "U.password_hash";
    private static final String USER_REGISTRATION_DATE = "U.registration_date";
    private static final String USER_PHONE_NUMBER = "U.phone_number";
    private static final String USER_LAST_LOGIN = "U.last_login";
    private static final String ROLE_NAME = "R.name";
    private static final String COUNTRY_NAME = "C.name";
    private static final String GENDER_NAME = "G.name";

    private final ConnectionPool pool;

    public SqlUserDao() {
        pool = ConnectionPool.INSTANCE;
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User search(SearchCriteria criteria) {
        return null;
    }

    @Override
    public User authorize(String email, int passwordHash) throws DaoException {
        Connection connection = pool.takeConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user;

        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt(USER_PASSWORD_HASH) != passwordHash) {
                    return null;
                }

                user = new User();

                user.setId(resultSet.getInt(USER_ID));
                user.setName(resultSet.getString(USER_NAME));
                user.setEmail(email);
                user.setRegistrationDate(DaoUtil.toJavaTime(resultSet.getTimestamp(USER_REGISTRATION_DATE)));
                user.setPhoneNumber(resultSet.getLong(USER_PHONE_NUMBER));
                user.setLastLoginDate(DaoUtil.toJavaTime(resultSet.getTimestamp(USER_LAST_LOGIN)));
                user.setUserRole(resultSet.getString(ROLE_NAME));
                user.setCountry(resultSet.getString(COUNTRY_NAME));
                user.setGender(resultSet.getString(GENDER_NAME));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading from database", e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
