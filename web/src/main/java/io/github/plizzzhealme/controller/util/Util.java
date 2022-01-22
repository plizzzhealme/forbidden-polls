package io.github.plizzzhealme.controller.util;

public final class Util {

    public static final String CONTROLLER = "controller";

    // user parameters
    public static final String USER = "user";
    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String BIRTHDAY = "birthday";
    public static final String CONFIRM_PASSWORD = "confirm_password";
    public static final String GENDER = "gender";
    public static final String COUNTRY = "country";
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    public static final String ADMIN = "admin";

    // pages
    public static final String SIGN_IN_JSP = "/WEB-INF/jsp/signIn.jsp";
    public static final String SIGN_UP_JSP = "/WEB-INF/jsp/signUp.jsp";
    public static final String START_JSP = "/WEB-INF/jsp/start.jsp";
    public static final String USER_JSP = "/WEB-INF/jsp/user.jsp";
    public static final String PAGE_NOT_FOUND_JSP = "error/pageNotFound.jsp";
    public static final String SERVER_ERROR_JSP = "error/serverError.jsp";
    public static final String SURVEY_JSP = "/WEB-INF/jsp/survey.jsp";
    public static final String PROFILE_JSP = "/WEB-INF/jsp/profile.jsp";
    public static final String CATEGORIES_JSP = "/WEB-INF/jsp/categories.jsp";
    public static final String CATEGORY_JSP = "/WEB-INF/jsp/category.jsp";
    public static final String SURVEY_END_JSP = "/WEB-INF/jsp/surveyEnd.jsp";
    public static final String SURVEY_BEGIN_JSP = "/WEB-INF/jsp/surveyBegin.jsp";


    // errors
    public static final String ERROR_MESSAGE = "error_message";
    public static final String EMPTY_FIELDS_ERROR = "empty_fields";
    public static final String INVALID_CREDENTIALS_ERROR = "invalid_credentials";
    public static final String PASSWORD_MISMATCH_ERROR = "password_mismatch";
    public static final String EMAIL_IS_BUSY_ERROR = "email_is_busy";

    // not classified
    public static final String URL = "url";
    public static final String LOCALE = "locale";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";
    public static final String COMMAND = "command";
    public static final String SURVEY_ID = "survey_id";

    // actions
    public static final String SIGN_IN_COMMAND = "sign_in";
    public static final String SIGN_UP_COMMAND = "sign_up";
    public static final String CHANGE_LOCALE_COMMAND = "change_locale";
    public static final String UNKNOWN_COMMAND = "unknown";
    public static final String SIGN_OUT_COMMAND = "sign_out";
    public static final String START_SURVEY_COMMAND = "start_survey";
    public static final String ANSWER_COMMAND = "answer";

    // navigation
    public static final String TO_SIGN_IN_PAGE_COMMAND = "to_sign_in_page";
    public static final String TO_SIGN_UP_PAGE_COMMAND = "to_sign_up_page";
    public static final String TO_START_PAGE_COMMAND = "to_start_page";
    public static final String TO_USER_PAGE_COMMAND = "to_user_page";
    public static final String TO_SURVEY_PAGE_COMMAND = "to_survey_page";
    public static final String TO_PROFILE_PAGE_COMMAND = "to_profile_page";
    public static final String TO_CATEGORIES_PAGE_COMMAND = "to_categories_page";
    public static final String TO_CATEGORY_PAGE_COMMAND = "to_category_page";
    public static final String TO_SURVEY_END_PAGE_COMMAND = "to_survey_end_page";
    public static final String TO_SURVEY_BEGIN_PAGE_COMMAND = "to_survey_begin_page";

    public static final String SURVEY = "survey";
    public static final String CATEGORY_LIST = "category_list";
    public static final String SURVEY_LIST = "survey_list";
    public static final String QUESTION_INDEX = "question_index";
    public static final String OPTION = "option";

    public static final String REDIRECT_URL_PATTERN = CONTROLLER + "?" + COMMAND + "=";


    private Util() {
    }
}
