package io.github.plizzzhealme.dao.util;

import com.lambdaworks.crypto.SCryptUtil;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DaoUtil {

    public static final String CHECK_IF_USER_EXISTS = "" +
            "SELECT EXISTS(SELECT id FROM users WHERE email = ?)";

    public static final String SELECT_USER_BY_EMAIL_SQL = "" +
            "SELECT U.id, U.hashed_password FROM users AS U WHERE  U.email = ?";

    public static final String SELECT_USER_BY_ID_SQL = "" +
            "SELECT U.name, U.email, U.registration_date, U.birthday, R.name, C.name, G.name " +
            "FROM users AS U " +
            "JOIN user_roles AS R ON U.user_role_id = R.id " +
            "JOIN countries AS C ON U.country_id = C.id " +
            "JOIN genders AS G ON U.gender_id = G.id " +
            "WHERE  U.id = ?";

    public static final String CREATE_NEW_USER_SQL = "" +
            "INSERT INTO users " +
            "(name, email, hashed_password, registration_date, birthday, user_role_id, country_id, gender_id) " +
            "VALUES (?, ?, ?, ?, ?, " +
            "(SELECT id FROM forbidden_polls.user_roles WHERE name=?), " +
            "(SELECT id FROM forbidden_polls.countries WHERE countries.iso_code=?), " +
            "(SELECT id FROM forbidden_polls.genders WHERE name=?))";

    private static final int CPU_COST_PARAM = 16;
    private static final int MEMORY_COST_PARAM = 16;
    private static final int PARALLELIZATION_PARAM = 16;

    private DaoUtil() {
    }

    public static LocalDateTime toJavaTime(Timestamp sqlTime) {
        if (sqlTime == null) {
            return null;
        }
        return sqlTime.toLocalDateTime();
    }

    public static LocalDate toJavaTime(Date sqlTime) {
        if (sqlTime == null) {
            return null;
        }
        return sqlTime.toLocalDate();
    }

    public static Timestamp toSqlTime(LocalDateTime javaTime) {
        if (javaTime == null) {
            return null;
        }
        return Timestamp.valueOf(javaTime);
    }

    public static Date toSqlTime(LocalDate javaTime) {
        if (javaTime == null) {
            return null;
        }
        return Date.valueOf(javaTime);
    }

    public static String hashPassword(String password) {
        return SCryptUtil.scrypt(password, CPU_COST_PARAM, MEMORY_COST_PARAM, PARALLELIZATION_PARAM);
    }

    public static boolean isCorrectPassword(String password, String hashedPassword) {
        if (!"".equals(password)
                && !"".equals(hashedPassword)) {
            return SCryptUtil.check(password, hashedPassword);
        }
        return false;
    }
}
