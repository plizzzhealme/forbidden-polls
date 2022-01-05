package io.github.plizzzhealme.dao.pool;

public final class DatabaseInfo {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/forbidden_polls";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final int POOL_SIZE = 5;

    private DatabaseInfo() {
    }
}
