package io.github.plizzzhealme.dao.util;

import com.lambdaworks.crypto.SCryptUtil;

import java.sql.Timestamp;
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

    public static Timestamp toSqlTime(LocalDateTime javaTime) {
        if (javaTime == null) {
            return null;
        }
        return Timestamp.valueOf(javaTime);
    }

    public static String hashPassword(String password) {
        return SCryptUtil.scrypt(password, CPU_COST_PARAM, MEMORY_COST_PARAM, PARALLELIZATION_PARAM);
    }
}
