package io.github.plizzzhealme.web.command.util;

public final class WebUtil {

    // common attributes and parameters
    public static final String ERROR = "error_message";
    public static final String USER = "user";

    // user's parameters
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirm_password";
    public static final String GENDER = "gender";
    public static final String COUNTRY = "country";

    // pages
    public static final String AUTHORIZATION_JSP = "/WEB-INF/jsp/Authorization.jsp";
    public static final String REGISTRATION_JSP = "/WEB-INF/jsp/Registration.jsp";
    public static final String START_JSP = "/WEB-INF/jsp/Start.jsp";
    public static final String USER_JSP = "/WEB-INF/jsp/User.jsp";

    // others
    public static final String TO_USER_PAGE_REDIRECT = "controller?command=to_user_page";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";

    private WebUtil() {
    }
}
