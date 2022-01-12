package io.github.plizzzhealme.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ControllerUtil {

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

    // pages
    public static final String AUTHORIZATION_JSP = "/WEB-INF/jsp/authorization.jsp";
    public static final String REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";
    public static final String START_JSP = "/WEB-INF/jsp/start.jsp";
    public static final String USER_JSP = "/WEB-INF/jsp/user.jsp";
    public static final String PAGE_NOT_FOUND_JSP = "error/pageNotFound.jsp";
    public static final String SERVER_ERROR_JSP = "error/serverError.jsp";

    // redirects
    public static final String TO_USER_PAGE_REDIRECT = "controller?command=to_user_page";
    public static final String TO_AUTHORIZATION_PAGE_REDIRECT = "controller?command=to_authorization_page";

    // errors
    public static final String ERROR_MESSAGE = "error_message";
    public static final String EMPTY_FIELDS_ERROR = "empty_fields";
    public static final String INVALID_CREDENTIALS_ERROR = "invalid_credentials";
    public static final String PASSWORD_MISMATCH_ERROR = "password_mismatch";
    public static final String EMAIL_IS_BUSY_ERROR = "email_is_busy";

    // not classified
    public static final String URL = "url";
    public static final String LOCALE = "locale";

    private ControllerUtil() {
    }

    public static void saveUrlToSession(HttpServletRequest request) {
        List<String> parameters = Collections.list(request.getParameterNames());
        String url = parameters
                .stream()
                .map(p -> p + "=" + request.getParameter(p))
                .collect(Collectors.joining("&", request.getRequestURL().append("?"), ""));

        request.getSession().setAttribute(ControllerUtil.URL, url);
    }
}
