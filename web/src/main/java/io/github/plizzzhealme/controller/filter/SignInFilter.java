package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SignInFilter implements Filter {

    private final Set<String> commandsRequiringToBeSignedIn = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        commandsRequiringToBeSignedIn.add(Util.TO_USER_PAGE_COMMAND);
        commandsRequiringToBeSignedIn.add(Util.TO_SURVEY_PAGE_COMMAND);
        commandsRequiringToBeSignedIn.add(Util.TO_PROFILE_PAGE_COMMAND);
        commandsRequiringToBeSignedIn.add(Util.TO_CATEGORIES_PAGE_COMMAND);
        commandsRequiringToBeSignedIn.add(Util.TO_CATEGORY_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(Util.COMMAND);

        if (commandsRequiringToBeSignedIn.contains(command)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();

            if (session.getAttribute(Util.USER_ID) == null) {
                ((HttpServletResponse) servletResponse).sendRedirect(Util.TO_SIGN_IN_PAGE_REDIRECT);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
