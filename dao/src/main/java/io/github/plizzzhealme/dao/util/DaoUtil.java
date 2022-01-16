package io.github.plizzzhealme.dao.util;

import com.lambdaworks.crypto.SCryptUtil;
import io.github.plizzzhealme.bean.criteria.Column;
import io.github.plizzzhealme.bean.criteria.Criteria;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public final class DaoUtil {

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

    public static String buildSearchSql(Criteria criteria, String table) {
        return criteria.getSearchParameters().keySet()
                .stream()
                .map(p -> p.getValue() + " = ?")
                .collect(Collectors.joining(" AND ", "SELECT * FROM " + table + " WHERE ", ""));
    }

    public static void setSearchParameters(Criteria criteria, PreparedStatement preparedStatement) throws SQLException {
        int i = 1;

        for (Column column : criteria.getSearchParameters().keySet()) {
            preparedStatement.setString(i, criteria.getSearchParameters().get(column));
            i++;
        }
    }
}
