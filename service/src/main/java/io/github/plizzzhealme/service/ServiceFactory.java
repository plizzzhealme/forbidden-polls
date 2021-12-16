package io.github.plizzzhealme.service;

public final class ServiceFactory {

    private static final UserService userService = new UserService();

    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return userService;
    }
}
