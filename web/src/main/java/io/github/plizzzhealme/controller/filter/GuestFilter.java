package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GuestFilter implements Filter {

    private final Set<String> guestCommands = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        guestCommands.add(Util.TO_SIGN_IN_PAGE_COMMAND);
        guestCommands.add(Util.SIGN_IN_COMMAND);
        guestCommands.add(Util.TO_SIGN_UP_PAGE_COMMAND);
        guestCommands.add(Util.SIGN_UP_COMMAND);
        guestCommands.add(Util.TO_HOME_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(Util.COMMAND);

        if (guestCommands.contains(command)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            Object id = session.getAttribute(Util.USER_ID);

            if (id == null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_PROFILE_PAGE_COMMAND);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
