package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UserFilter implements Filter {

    private final Set<String> userCommands = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        userCommands.add(Util.ANSWER_SURVEY_QUESTION_COMMAND);
        userCommands.add(Util.EDIT_PROFILE_INFO_COMMAND);
        userCommands.add(Util.SIGN_OUT_COMMAND);
        userCommands.add(Util.TAKE_SURVEY_COMMAND);
        userCommands.add(Util.TO_CATEGORIES_PAGE_COMMAND);
        userCommands.add(Util.TO_CATEGORY_PAGE_COMMAND);
        userCommands.add(Util.TO_COMPLETED_SURVEYS_PAGE_COMMAND);
        userCommands.add(Util.TO_EDIT_PROFILE_INFO_PAGE_COMMAND);
        userCommands.add(Util.TO_PROFILE_INFO_PAGE_COMMAND);
        userCommands.add(Util.TO_PROFILE_PAGE_COMMAND);
        userCommands.add(Util.TO_SURVEY_COMPLETED_PAGE_COMMAND);
        userCommands.add(Util.TO_SURVEY_HEADER_PAGE_COMMAND);
        userCommands.add(Util.TO_SURVEY_QUESTION_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(Util.COMMAND);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if (userCommands.contains(command)) {
            if (session.getAttribute(Util.USER_ID) != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SIGN_IN_PAGE_COMMAND);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
