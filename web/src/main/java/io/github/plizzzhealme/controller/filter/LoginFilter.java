package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.command.CommandProvider;
import io.github.plizzzhealme.controller.util.ControllerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("HELLO!");

        if (CommandProvider.TO_USER_PAGE_COMMAND.equals(servletRequest.getParameter(CommandProvider.COMMAND))) {
            if (servletRequest.getAttribute(ControllerUtil.USER) == null) {
                System.out.println("IF!");
                ((HttpServletResponse) servletResponse).sendRedirect(ControllerUtil.TO_AUTHORIZATION_PAGE_REDIRECT);
            } else {
                System.out.println("ELSE!");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
