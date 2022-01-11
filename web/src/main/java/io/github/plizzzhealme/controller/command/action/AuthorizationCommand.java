package io.github.plizzzhealme.controller.command.action;

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

        String email = request.getParameter(ControllerUtil.EMAIL);
        String password = request.getParameter(ControllerUtil.PASSWORD);
        HttpSession session = request.getSession();

        if (StringUtils.isAnyBlank(email, password)) { // if email or password are not entered
            session.setAttribute(ControllerUtil.ERROR_MESSAGE, ControllerUtil.EMPTY_FIELDS_ERROR);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.AUTHORIZATION_JSP);
            dispatcher.forward(request, response);
        } else { // if entered
            UserService userService = ServiceFactory.INSTANCE.getUserService();
            int id = userService.authorize(email, password);

            if (id == 0) { // if invalid email or password
                session.setAttribute(ControllerUtil.ERROR_MESSAGE, ControllerUtil.INVALID_CREDENTIALS_ERROR);

                RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.AUTHORIZATION_JSP);
                dispatcher.forward(request, response);
            } else { // if ok
                session.removeAttribute(ControllerUtil.ERROR_MESSAGE);
                session.setAttribute(ControllerUtil.USER_ID, id);

                response.sendRedirect(ControllerUtil.TO_USER_PAGE_REDIRECT);
            }
        }
    }
}
