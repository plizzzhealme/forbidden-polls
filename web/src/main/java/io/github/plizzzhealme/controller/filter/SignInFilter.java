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

    private final Set<String> signInCommands = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        signInCommands.add(Util.EDIT_PROFILE_INFO_COMMAND);
        signInCommands.add(Util.SIGN_OUT_COMMAND);
        signInCommands.add(Util.TO_EDIT_PROFILE_INFO_PAGE_COMMAND);
        signInCommands.add(Util.TO_PROFILE_INFO_PAGE_COMMAND);
        signInCommands.add(Util.TO_PROFILE_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(Util.COMMAND);

        if (signInCommands.contains(command)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();

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
