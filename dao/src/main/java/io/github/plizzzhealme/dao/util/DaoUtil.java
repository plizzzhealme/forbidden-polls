package io.github.plizzzhealme.dao.util;

import com.lambdaworks.crypto.SCryptUtil;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
