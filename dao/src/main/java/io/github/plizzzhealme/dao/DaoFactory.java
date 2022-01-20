package io.github.plizzzhealme.dao;

import io.github.plizzzhealme.dao.sql.*;

public enum DaoFactory {

    INSTANCE;

    private final UserDao userDao = new SqlUserDao();
    private final ConnectionDao connectionDao = new SqlConnectionDao();
    private final UserRoleDao userRoleDao = new SqlUserRoleDao();
    private final SurveyDao surveyDao = new SqlSurveyDao();
    private final QuestionDao questionDao = new SqlQuestionDao();
    private final OptionDao optionDao = new SqlOptionDao();
    private final CategoryDao categoryDao = new SqlCategoryDao();

    public UserDao getUserDao() {
        return userDao;
    }

    public ConnectionDao getConnectionDao() {
        return connectionDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public SurveyDao getSurveyDao() {
        return surveyDao;
    }

    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    public OptionDao getOptionDao() {
        return optionDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
}
