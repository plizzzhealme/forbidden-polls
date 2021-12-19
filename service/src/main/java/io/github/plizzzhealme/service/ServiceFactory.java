package io.github.plizzzhealme.service;

public final class ServiceFactory {

    private static final UserService USER_SERVICE = new UserService();
    private static final DatabaseService DATABASE_SERVICE = new DatabaseService();

    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }

    public static DatabaseService getPoolService() {
        return DATABASE_SERVICE;
    }
}
