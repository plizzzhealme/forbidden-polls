package io.github.plizzzhealme.dao.util;

import io.github.plizzzhealme.bean.criteria.Parameter;

import java.util.EnumMap;

public final class SqlParameter {

    public static final String USERS = "forbidden_polls.users";
    public static final String USERS_ID = "users.id";
    public static final String USERS_EMAIL = "users.email";
    public static final String USERS_NAME = "users.name";
    public static final String USERS_BIRTHDAY = "users.birthday";
    public static final String USERS_REGISTRATION_DATE = "users.registration_date";
    public static final String USERS_HASHED_PASSWORD = "users.hashed_password";

    public static final String SURVEYS_ID = "surveys.id";
    public static final String SURVEYS_NAME = "surveys.name";
    public static final String SURVEYS_IMAGE_URL = "surveys.image_url";
    public static final String SURVEYS_INSTRUCTIONS = "surveys.instructions";
    public static final String SURVEYS_CREATION_DATE = "surveys.creation_date";
    public static final String SURVEYS_DESCRIPTION = "surveys.description";
    public static final String SURVEYS_CATEGORY_ID = "surveys.category_id";

    public static final String CATEGORIES_NAME = "categories.name";

    private static final EnumMap<Parameter, String> PARAMETERS = new EnumMap<>(Parameter.class);
    public static final String CATEGORIES_ID = "categories.id";

    static {
        PARAMETERS.put(Parameter.USER_ID, USERS_ID);
        PARAMETERS.put(Parameter.USER_NAME, USERS_NAME);
        PARAMETERS.put(Parameter.USER_EMAIL, USERS_EMAIL);
        PARAMETERS.put(Parameter.USER_BIRTHDAY, USERS_BIRTHDAY);
        PARAMETERS.put(Parameter.USER_REGISTRATION_DATE, USERS_REGISTRATION_DATE);

        PARAMETERS.put(Parameter.SURVEY_ID, SURVEYS_ID);
        PARAMETERS.put(Parameter.SURVEY_NAME, SURVEYS_NAME);
        PARAMETERS.put(Parameter.SURVEY_IMAGE_URL, SURVEYS_IMAGE_URL);
        PARAMETERS.put(Parameter.SURVEY_INSTRUCTIONS, SURVEYS_INSTRUCTIONS);
        PARAMETERS.put(Parameter.SURVEY_CREATION_DATE, SURVEYS_CREATION_DATE);
        PARAMETERS.put(Parameter.SURVEY_DESCRIPTION, SURVEYS_DESCRIPTION);
        PARAMETERS.put(Parameter.SURVEY_CATEGORY_ID, SURVEYS_CATEGORY_ID);

    }

    private SqlParameter() {
    }

    public static String getSqlParameter(Parameter parameter) {
        return PARAMETERS.get(parameter);
    }
}
