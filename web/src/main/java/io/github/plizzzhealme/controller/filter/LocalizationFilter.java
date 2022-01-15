package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.command.CommandProvider;
import io.github.plizzzhealme.controller.util.ControllerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/controller")
public class LocalizationFilter implements Filter {

    private final Set<String> commandsToBeSaved = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        commandsToBeSaved.add(CommandProvider.TO_USER_PAGE_COMMAND);
        commandsToBeSaved.add(CommandProvider.TO_AUTHORIZATION_PAGE_COMMAND);
        commandsToBeSaved.add(CommandProvider.TO_START_PAGE_COMMAND);
        commandsToBeSaved.add(CommandProvider.TO_REGISTRATION_PAGE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String command = servletRequest.getParameter(CommandProvider.COMMAND);

        if (commandsToBeSaved.contains(command)) {
            ControllerUtil.saveUrlToSession((HttpServletRequest) servletRequest);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
