package io.github.plizzzhealme.dao.util;

import com.lambdaworks.crypto.SCryptUtil;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public final class Util {

    private static final int CPU_COST_PARAM = 16;
    private static final int MEMORY_COST_PARAM = 16;
    private static final int PARALLELIZATION_PARAM = 16;

    private Util() {
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

    public static String buildSearchSql(SearchCriteria criteria, String table) {
        if (criteria.getSearchParameters().isEmpty()) {
            return "SELECT * FROM " + table;
        }

        return criteria.getSearchParameters().keySet()
                .stream()
                .map(parameter -> {
                    if (isIntParameter(parameter)) {
                        return SqlParameter.getSqlParameter(parameter) + " = ?";
                    } else {
                        return SqlParameter.getSqlParameter(parameter) + " LIKE ?";
                    }
                })
                .collect(Collectors.joining(" AND ", "SELECT * FROM " + table + " WHERE ", ""));
    }

    private static boolean isIntParameter(Parameter parameter) {
        return parameter == Parameter.OPTION_ID
                || parameter == Parameter.SURVEY_ID
                || parameter == Parameter.SURVEY_CATEGORY_ID
                || parameter == Parameter.USER_ID
                || parameter == Parameter.QUESTION_ID
                || parameter == Parameter.QUESTION_CATEGORY_ID;
    }

    public static void setSearchParameters(SearchCriteria criteria, PreparedStatement preparedStatement)
            throws SQLException {

        int i = 1;

        for (Parameter parameter : criteria.getSearchParameters().keySet()) {
            if (isIntParameter(parameter)) {
                preparedStatement.setObject(i, criteria.getSearchParameters().get(parameter));
            } else {
                preparedStatement.setObject(i, "%" + criteria.getSearchParameters().get(parameter) + "%");
            }

            i++;
        }
    }
}
