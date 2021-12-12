package io.github.plizzzhealme.dao.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class DaoUtil {

    private DaoUtil() {}

    public static LocalDateTime toLocalDateTime(Timestamp sqlTime) {
        if (sqlTime == null) {
            return null;
        }

        return sqlTime.toLocalDateTime();
    }
}
