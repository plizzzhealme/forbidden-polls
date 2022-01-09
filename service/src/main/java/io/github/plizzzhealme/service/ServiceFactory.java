package io.github.plizzzhealme.service;

import io.github.plizzzhealme.service.impl.ConnectionServiceImpl;
import io.github.plizzzhealme.service.impl.UserServiceImpl;

public enum ServiceFactory {

    INSTANCE;

    private final UserService userService = new UserServiceImpl();
    private final ConnectionService connectionService = new ConnectionServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public ConnectionService getConnectionService() {
        return connectionService;
    }
}
