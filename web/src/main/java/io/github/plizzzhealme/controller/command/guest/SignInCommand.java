package io.github.plizzzhealme.controller.command.guest;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.exception.EmptyInputException;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.controller.validator.EmptyInputValidator;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.InvalidCredentialsException;
import io.github.plizzzhealme.service.exception.ServiceException;

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

        String email = request.getParameter(Util.USER_EMAIL);
        String password = request.getParameter(Util.USER_PASSWORD);

        try {
            EmptyInputValidator.getInstance().validateEmptyInput(email, password);

            User user = ServiceFactory.INSTANCE.getUserService().signIn(email, password);

            HttpSession session = request.getSession();
            session.setAttribute(Util.USER_ID, user.getId());
            session.setAttribute(Util.USER_ROLE, user.getUserRole());

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_PROFILE_PAGE_COMMAND);
        } catch (InvalidCredentialsException e) {
            request.setAttribute(Util.ERROR, Util.INVALID_CREDENTIALS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_IN_JSP);
            dispatcher.forward(request, response);
        } catch (EmptyInputException e) {
            request.setAttribute(Util.ERROR, Util.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }
    }
}
