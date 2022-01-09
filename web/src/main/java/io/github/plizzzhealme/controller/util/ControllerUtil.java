package io.github.plizzzhealme.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class ControllerUtil {

    // common attributes and parameters
    public static final String ERROR = "error_message";
    public static final String USER = "user";
    public static final String URL = "url";
    public static final String LOCALE = "locale";
    public static final String REFERER = "referer";

    // user's parameters
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String BIRTHDAY = "birthday";
    public static final String CONFIRM_PASSWORD = "confirm_password";
    public static final String GENDER = "gender";
    public static final String COUNTRY = "country";

    // pages
    public static final String AUTHORIZATION_JSP = "/WEB-INF/jsp/authorization.jsp";
    public static final String REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";
    public static final String START_JSP = "/WEB-INF/jsp/start.jsp";
    public static final String USER_JSP = "/WEB-INF/jsp/user.jsp";

    // redirects
    public static final String TO_USER_PAGE_REDIRECT = "controller?command=to_user_page";
    public static final String TO_SERVER_ERROR_PAGE_REDIRECT = "error/serverError.jsp";
    public static final String TO_AUTHORIZATION_PAGE_REDIRECT = "controller?command=to_authorization_page";
    public static final String TO_UNFOUNDED_PAGE_REDIRECT = "error/pageNotFound.jsp";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";

    private ControllerUtil() {
    }

    public static void saveUrlToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String url = request.getRequestURL().append("?").append(request.getQueryString()).toString();
        session.setAttribute(ControllerUtil.URL, url);
    }
}
