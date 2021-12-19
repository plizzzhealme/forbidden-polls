package io.github.plizzzhealme.service;

public final class ServiceFactory {

    private static final UserService USER_SERVICE = new UserService();
    private static final DatabaseConnectionService DATABASE_CONNECTION_SERVICE = new DatabaseConnectionService();

    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }

    public static DatabaseConnectionService getDatabaseConnectionService() {
        return DATABASE_CONNECTION_SERVICE;
    }
}
