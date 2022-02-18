package io.github.plizzzhealme.dao.util;

import io.github.plizzzhealme.bean.criteria.Parameter;

import java.util.EnumMap;

public final class SqlParameter {

    public static final String USERS_ID = "users.id";
    public static final String USERS_EMAIL = "users.email";
    public static final String USERS_NAME = "users.name";
    public static final String USERS_BIRTHDAY = "users.birthday";
    public static final String USERS_REGISTRATION_DATE = "users.registration_date";
    public static final String USERS_HASHED_PASSWORD = "users.hashed_password";

    public static final String SURVEYS = "surveys";
    public static final String SURVEYS_ID = "surveys.id";
    public static final String SURVEYS_NAME = "surveys.name";
    public static final String SURVEYS_IMAGE_URL = "surveys.image_url";
    public static final String SURVEYS_INSTRUCTIONS = "surveys.instructions";
    public static final String SURVEYS_CREATION_DATE = "surveys.creation_date";
    public static final String SURVEYS_DESCRIPTION = "surveys.description";
    public static final String SURVEYS_CATEGORY_ID = "surveys.category_id";

    public static final String CATEGORIES_ID = "categories.id";
    public static final String CATEGORIES_NAME = "categories.name";

    public static final String USER_ROLES_NAME = "user_roles.name";

    public static final String COUNTRIES_NAME = "countries.name";

    public static final String GENDERS_NAME = "genders.name";

    public static final String OPTIONS_ID = "options.id";
    public static final String OPTIONS_INDEX_NUMBER = "options.index_number";
    public static final String OPTIONS_BODY = "options.body";

    public static final String QUESTIONS_ID = "questions.id";
    public static final String QUESTIONS_BODY = "questions.body";
    public static final String QUESTIONS_INDEX_NUMBER = "questions.index_number";
    public static final String QUESTIONS_IMAGE_URL = "questions.image_url";
    public static final String QUESTIONS_DESCRIPTION = "questions.description";

    public static final String OPTION_TYPES_TYPE = "option_types.type";

    private static final EnumMap<Parameter, String> PARAMETERS = new EnumMap<>(Parameter.class);

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

        PARAMETERS.put(Parameter.GENDER_NAME, GENDERS_NAME);

    }

    private SqlParameter() {
    }

    public static String getSqlParameter(Parameter parameter) {
        return PARAMETERS.get(parameter);
    }
}
