package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.command.CommandProvider;
import io.github.plizzzhealme.controller.util.ControllerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/controller")
public class AuthorizationFilter implements Filter {

    private final Set<String> commandsRequiringAuthorization = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        commandsRequiringAuthorization.add(CommandProvider.TO_USER_PAGE_COMMAND);
        commandsRequiringAuthorization.add(CommandProvider.TO_SURVEY_PAGE_COMMAND);
        commandsRequiringAuthorization.add(CommandProvider.TO_PROFILE_PAGE_COMMAND);
        commandsRequiringAuthorization.add(CommandProvider.TO_CATEGORIES_PAGE_COMMAND);
        commandsRequiringAuthorization.add(CommandProvider.TO_CATEGORY_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(CommandProvider.COMMAND);

        if (commandsRequiringAuthorization.contains(command)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();

            if (session.getAttribute(ControllerUtil.USER_ID) == null) {
                ((HttpServletResponse) servletResponse).sendRedirect(ControllerUtil.TO_AUTHORIZATION_PAGE_REDIRECT);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
