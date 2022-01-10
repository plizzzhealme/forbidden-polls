package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.ControllerUtil;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        ControllerUtil.saveUrlToSession(request);

        String email = request.getParameter(ControllerUtil.EMAIL);
        String password = request.getParameter(ControllerUtil.PASSWORD);

        User user;

        if (StringUtils.isAnyBlank(email, password)) {

            request.getSession().setAttribute("error_message", "empty");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.AUTHORIZATION_JSP);
            dispatcher.forward(request, response);
        } else {
            UserService userService = ServiceFactory.INSTANCE.getUserService();
            user = userService.authorize(email, password);

            if (user == null) {
                request.getSession().setAttribute("error_message", "invalid");
                RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.AUTHORIZATION_JSP);
                dispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession(true);
                session.removeAttribute("error_message");
                session.setAttribute(ControllerUtil.USER, user);
                response.sendRedirect(ControllerUtil.TO_USER_PAGE_REDIRECT);
            }
        }
    }
}
