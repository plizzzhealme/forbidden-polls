package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AdminFilter implements Filter {

    private final Set<String> adminCommands = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        adminCommands.add(Util.ADD_HEADER_COMMAND);
        adminCommands.add(Util.ADD_QUESTION_COMMAND);
        adminCommands.add(Util.ADD_SURVEY_COMMAND);
        adminCommands.add(Util.EDIT_SURVEY_COMMAND);
        adminCommands.add(Util.SEARCH_SURVEY_COMMAND);
        adminCommands.add(Util.SEARCH_NEXT_USER_COMMAND);
        adminCommands.add(Util.SEARCH_PREVIOUS_USER_COMMAND);
        adminCommands.add(Util.SEARCH_USER_COMMAND);

        adminCommands.add(Util.TO_ADD_HEADER_PAGE_COMMAND);
        adminCommands.add(Util.TO_ADD_QUESTION_PAGE_COMMAND);
        adminCommands.add(Util.TO_ADD_SURVEY_PAGE_COMMAND);
        adminCommands.add(Util.TO_SURVEY_PAGE_COMMAND);
        adminCommands.add(Util.TO_SEARCH_SURVEY_PAGE_COMMAND);
        adminCommands.add(Util.TO_SEARCH_USER_PAGE_COMMAND);
        adminCommands.add(Util.TO_SURVEY_ADDED_PAGE_COMMAND);
        adminCommands.add(Util.TO_USER_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(Util.COMMAND);

        if (adminCommands.contains(command)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();

            if (User.ADMIN_ROLE.equals(session.getAttribute(Util.USER_ROLE))) {
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
