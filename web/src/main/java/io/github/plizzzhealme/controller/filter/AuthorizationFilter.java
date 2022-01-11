package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.command.CommandProvider;
import io.github.plizzzhealme.controller.util.ControllerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/controller")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(CommandProvider.COMMAND);

        if (CommandProvider.TO_USER_PAGE_COMMAND.equals(command)) { // if command = to_user_page
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();

            if (session.getAttribute(ControllerUtil.USER_ID) == null) { // if logged in
                ((HttpServletResponse) servletResponse).sendRedirect(ControllerUtil.TO_AUTHORIZATION_PAGE_REDIRECT);
            } else { // if not logged in
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else { // if other commands
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
