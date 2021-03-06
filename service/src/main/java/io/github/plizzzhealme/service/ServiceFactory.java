package io.github.plizzzhealme.service;

import io.github.plizzzhealme.service.impl.CategoryServiceImpl;
import io.github.plizzzhealme.service.impl.ConnectionServiceImpl;
import io.github.plizzzhealme.service.impl.SurveyServiceImpl;
import io.github.plizzzhealme.service.impl.UserServiceImpl;

public enum ServiceFactory {

    INSTANCE;

    private final UserService userService = new UserServiceImpl();
    private final ConnectionService connectionService = new ConnectionServiceImpl();
    private final SurveyService surveyService = new SurveyServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public ConnectionService getConnectionService() {
        return connectionService;
    }

    public SurveyService getSurveyService() {
        return surveyService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }
}
