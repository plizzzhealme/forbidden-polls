package io.github.plizzzhealme.service;

public final class ServiceFactory {

    private static final UserService userService = new UserService();
    private static final PoolService poolService = new PoolService();

    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return userService;
    }

    public static PoolService getPoolService() {
        return poolService;
    }
}
