package io.github.plizzzhealme.service;

import io.github.plizzzhealme.service.impl.ConnectionServiceImpl;
import io.github.plizzzhealme.service.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final UserServiceImpl USER_SERVICE = new UserServiceImpl();
    private static final ConnectionServiceImpl DATABASE_CONNECTION_SERVICE = new ConnectionServiceImpl();

    private ServiceFactory() {
    }

    public static UserServiceImpl getUserService() {
        return USER_SERVICE;
    }

    public static ConnectionServiceImpl getDatabaseConnectionService() {
        return DATABASE_CONNECTION_SERVICE;
    }
}
