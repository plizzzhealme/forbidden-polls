package io.github.plizzzhealme.controller.util;

public final class Util {

    public static final String CONTROLLER = "controller";
    public static final String CATEGORY = "category";

    // user parameters
    public static final String USER = "user";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_BIRTHDAY = "user_birthday";
    public static final String USER_GENDER = "user_gender";
    public static final String USER_COUNTRY = "user_country";
    public static final String USER_ROLE = "user_role";
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    public static final String ADMIN = "admin";

    // survey parameters
    public static final String SURVEY = "survey";
    public static final String NEW_SURVEY = "new_survey";
    public static final String SURVEY_LIST = "survey_list";
    public static final String SURVEY_ID = "survey_id";
    public static final String SURVEY_TITLE = "survey_title";
    public static final String SURVEY_DESCRIPTION = "survey_description";
    public static final String SURVEY_INSTRUCTIONS = "survey_instructions";
    public static final String SURVEY_IMAGE_URL = "survey_image_url";
    public static final String SURVEY_CATEGORY = "survey_category";

    public static final String QUESTION_BODY = "question_body";
    public static final String QUESTION_DESCRIPTION = "question_description";
    public static final String QUESTION_IMAGE_URL = "question_image_url";
    public static final String QUESTION_INDEX = "question_index";

    // pages
    public static final String SIGN_IN_JSP = "/WEB-INF/jsp/signIn.jsp";
    public static final String SIGN_UP_JSP = "/WEB-INF/jsp/signUp.jsp";
    public static final String HOME_JSP = "/WEB-INF/jsp/home.jsp";
    public static final String PROFILE_JSP = "/WEB-INF/jsp/profile.jsp";
    public static final String PAGE_NOT_FOUND_JSP = "error/pageNotFound.jsp";
    public static final String SERVER_ERROR_JSP = "error/serverError.jsp";
    public static final String QUESTION_JSP = "/WEB-INF/jsp/question.jsp";
    public static final String PROFILE_INFO_JSP = "/WEB-INF/jsp/profileInfo.jsp";
    public static final String CATEGORIES_JSP = "/WEB-INF/jsp/categories.jsp";
    public static final String CATEGORY_JSP = "/WEB-INF/jsp/category.jsp";
    public static final String SURVEY_COMPLETED_JSP = "/WEB-INF/jsp/surveyCompleted.jsp";
    public static final String HEADER_JSP = "/WEB-INF/jsp/header.jsp";
    public static final String COMPLETED_SURVEYS_JSP = "/WEB-INF/jsp/completedSurveys.jsp";
    public static final String ADD_HEADER_JSP = "/WEB-INF/jsp/addHeader.jsp";
    public static final String ADD_QUESTION_JSP = "/WEB-INF/jsp/addQuestion.jsp";
    public static final String SURVEY_ADDED_JSP = "/WEB-INF/jsp/surveyAdded.jsp";
    public static final String ADD_SURVEY_JSP = "/WEB-INF/jsp/addSurvey.jsp";
    public static final String EDIT_PROFILE_INFO_JSP = "/WEB-INF/jsp/editProfileInfo.jsp";
    public static final String STATISTICS_JSP = "/WEB-INF/jsp/statistics.jsp";
    public static final String SEARCH_COMMON_STATISTICS_JSP = "/WEB-INF/jsp/searchCommonStatistics.jsp";


    // errors
    public static final String ERROR = "error";
    public static final String EMPTY_FIELDS_ERROR = "empty_fields_error";
    public static final String INVALID_CREDENTIALS_ERROR = "invalid_credentials_error";
    public static final String PASSWORD_MISMATCH_ERROR = "password_mismatch_error";
    public static final String EMAIL_IS_BUSY_ERROR = "email_is_busy_error";

    // not classified
    public static final String URL = "url";
    public static final String LOCALE = "locale";
    public static final String CATEGORY_NAME = "category_name";
    public static final String COMMAND = "command";

    // actions
    public static final String SIGN_IN_COMMAND = "sign_in";
    public static final String SIGN_UP_COMMAND = "sign_up";
    public static final String CHANGE_LOCALE_COMMAND = "change_locale";
    public static final String UNKNOWN_COMMAND = "unknown";
    public static final String SIGN_OUT_COMMAND = "sign_out";
    public static final String TAKE_SURVEY_COMMAND = "take_survey";
    public static final String ANSWER_QUESTION_COMMAND = "answer_question";
    public static final String ADD_HEADER_COMMAND = "add_header";
    public static final String ADD_QUESTION_COMMAND = "add_question";
    public static final String ADD_SURVEY_COMMAND = "add_survey";
    public static final String EDIT_SURVEY_COMMAND = "edit_survey";
    public static final String EDIT_PROFILE_INFO_COMMAND = "edit_profile_info";

    public static final String SEARCH_COMMON_STATISTICS_COMMAND = "search_common_statistics";


    // navigation
    public static final String TO_SIGN_IN_PAGE_COMMAND = "to_sign_in_page";
    public static final String TO_SIGN_UP_PAGE_COMMAND = "to_sign_up_page";
    public static final String TO_HOME_PAGE_COMMAND = "to_home_page";
    public static final String TO_PROFILE_PAGE_COMMAND = "to_profile_page";
    public static final String TO_QUESTION_PAGE_COMMAND = "to_question_page";
    public static final String TO_PROFILE_INFO_PAGE_COMMAND = "to_profile_info_page";
    public static final String TO_CATEGORIES_PAGE_COMMAND = "to_categories_page";
    public static final String TO_CATEGORY_PAGE_COMMAND = "to_category_page";
    public static final String TO_SURVEY_COMPLETED_PAGE_COMMAND = "to_survey_completed_page";
    public static final String TO_HEADER_PAGE_COMMAND = "to_header_page";
    public static final String TO_COMPLETED_SURVEYS_PAGE_COMMAND = "to_completed_surveys_page";
    public static final String TO_EDIT_PROFILE_INFO_PAGE_COMMAND = "to_edit_profile_info_page";
    public static final String TO_ADD_HEADER_PAGE_COMMAND = "to_add_header_page";
    public static final String TO_ADD_QUESTION_PAGE_COMMAND = "to_add_question_page";
    public static final String TO_SURVEY_ADDED_PAGE_COMMAND = "to_survey_added_page";
    public static final String TO_ADD_SURVEY_PAGE_COMMAND = "to_add_survey_page";
    public static final String TO_STATISTICS_PAGE_COMMAND = "to_statistics_page";

    public static final String TO_SEARCH_COMMON_STATISTICS_PAGE_COMMAND = "to_search_common_statistics_page";

    public static final String CATEGORY_LIST = "category_list";
    public static final String OPTION = "option";

    public static final String REDIRECT_URL_PATTERN = CONTROLLER + "?" + COMMAND + "=";
    public static final String EDIT_INDEX = "edit_index";


    private Util() {
    }
}
