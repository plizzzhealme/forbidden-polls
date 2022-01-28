package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
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

public class SignInCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        String email = request.getParameter(Util.EMAIL);
        String password = request.getParameter(Util.PASSWORD);

        if (StringUtils.isAnyBlank(email, password)) { // if email or password are not entered
            request.setAttribute(Util.ERROR_MESSAGE, Util.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_IN_JSP);
            dispatcher.forward(request, response);
        } else { // if entered
            UserService userService = ServiceFactory.INSTANCE.getUserService();
            User user = userService.authorize(email, password);
            //int userID = user.getId();

            if (user == null) { // if invalid email or password
                request.setAttribute(Util.ERROR_MESSAGE, Util.INVALID_CREDENTIALS_ERROR);

                RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_IN_JSP);
                dispatcher.forward(request, response);
            } else { // if ok
                HttpSession session = request.getSession();
                session.setAttribute(Util.USER_ID, user.getId());
                session.setAttribute(Util.USER_ROLE, user.getUserRole());

                response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_PROFILE_PAGE_COMMAND);
            }
        }
    }
}
