package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.command.CommandProvider;
import io.github.plizzzhealme.controller.util.ControllerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String command = servletRequest.getParameter(CommandProvider.COMMAND);

        if (CommandProvider.TO_USER_PAGE_COMMAND.equals(command)) {

            logger.info("attempt to get access to user page");

            HttpSession session = ((HttpServletRequest) servletRequest).getSession();

            if (session.getAttribute(ControllerUtil.USER) == null) {
                logger.error("user is not logged in, redirect to login page");

                ((HttpServletResponse) servletResponse).sendRedirect(ControllerUtil.TO_AUTHORIZATION_PAGE_REDIRECT);
            } else {
                logger.info("user is logged in");

                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            logger.info("Command = {} is not required to be logged in", command);

            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
