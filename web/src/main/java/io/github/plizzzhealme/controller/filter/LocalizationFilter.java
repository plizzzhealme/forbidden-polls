package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LocalizationFilter implements Filter {

    private final Set<String> commandsToBeSaved = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        commandsToBeSaved.add(Util.TO_PROFILE_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_SIGN_IN_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_START_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_SIGN_UP_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_PROFILE_INFO_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_CATEGORIES_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_CATEGORY_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_SURVEY_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_SURVEY_END_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_SURVEY_BEGIN_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_COMPLETED_SURVEYS_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_ADD_SURVEY_HEADER_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_SURVEY_ADDED_PAGE_COMMAND);
        commandsToBeSaved.add(Util.TO_EDIT_SURVEY_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String command = servletRequest.getParameter(Util.COMMAND);

        if (commandsToBeSaved.contains(command)) {
            saveUrlToSession((HttpServletRequest) servletRequest);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void saveUrlToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> parameters = Collections.list(request.getParameterNames());
        String url = parameters
                .stream()
                .map(p -> p + "=" + request.getParameter(p))
                .collect(Collectors.joining("&", request.getRequestURL().append("?"), ""));

        session.setAttribute(Util.URL, url);
    }
}
