package io.github.plizzzhealme.web.command.impl;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.web.command.Command;
import io.github.plizzzhealme.web.command.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(WebUtil.EMAIL);
        String password = request.getParameter(WebUtil.PASSWORD);

        UserService userService = ServiceFactory.getUserService();
        User user = userService.authorize(email, password);

        if (user != null) {
            password = null;
            HttpSession session = request.getSession(true);
            session.setAttribute(WebUtil.USER, user);
            response.sendRedirect(WebUtil.TO_USER_PAGE_REDIRECT);
        } else {
            request.setAttribute(WebUtil.ERROR, WebUtil.INVALID_EMAIL_OR_PASSWORD);
            RequestDispatcher dispatcher = request.getRequestDispatcher(WebUtil.AUTHORIZATION_JSP);
            dispatcher.forward(request, response);
        }
    }
}
