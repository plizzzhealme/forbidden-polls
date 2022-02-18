package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

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
        userCommands.add(Util.ANSWER_QUESTION_COMMAND);
        userCommands.add(Util.TAKE_SURVEY_COMMAND);

        userCommands.add(Util.TO_CATEGORIES_PAGE_COMMAND);
        userCommands.add(Util.TO_CATEGORY_PAGE_COMMAND);
        userCommands.add(Util.TO_COMPLETED_SURVEYS_PAGE_COMMAND);
        userCommands.add(Util.TO_HEADER_PAGE_COMMAND);
        userCommands.add(Util.TO_QUESTION_PAGE_COMMAND);
        userCommands.add(Util.TO_STATISTICS_PAGE_COMMAND);
        userCommands.add(Util.TO_SURVEY_COMPLETED_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        try {
            Object id = session.getAttribute(Util.USER_ID);

            if (id != null) {
                String userRole = ServiceFactory.INSTANCE.getUserService().readUserRole((int) id);
                session.setAttribute(Util.USER_ROLE, userRole);
            }
        } catch (ServiceException e) {
            throw new ServletException("Failed to read user info", e);
        }

        String command = servletRequest.getParameter(Util.COMMAND);

        if (userCommands.contains(command)) {
            if (User.USER_ROLE.equals(session.getAttribute(Util.USER_ROLE))) {
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
