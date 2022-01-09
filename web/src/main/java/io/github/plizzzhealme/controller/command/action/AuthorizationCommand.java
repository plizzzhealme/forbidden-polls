package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.ControllerUtil;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String email = request.getParameter(ControllerUtil.EMAIL);
        String password = request.getParameter(ControllerUtil.PASSWORD);

        UserService userService = ServiceFactory.INSTANCE.getUserService();
        User user = userService.authorize(email, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(ControllerUtil.USER, user);
            response.sendRedirect(ControllerUtil.TO_USER_PAGE_REDIRECT);
        } else {
            request.setAttribute(ControllerUtil.ERROR, ControllerUtil.INVALID_EMAIL_OR_PASSWORD);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.AUTHORIZATION_JSP);
            dispatcher.forward(request, response);
        }
    }
}
