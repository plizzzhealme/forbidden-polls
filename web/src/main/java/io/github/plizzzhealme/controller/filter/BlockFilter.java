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

public class BlockFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            checkIfBanned(servletRequest, servletResponse, filterChain);
        } catch (ServiceException e) {
            throw new ServletException("Failed to check user role", e);
        }
    }

    private void checkIfBanned(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServiceException, IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object id = session.getAttribute(Util.USER_ID);

        if (id != null) {
            String userRole = ServiceFactory.INSTANCE.getUserService().readUserRole((int) id);
            if (User.BANNED_ROLE.equals(userRole)) {
                request.getSession().invalidate();
                response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SIGN_IN_PAGE_COMMAND);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
