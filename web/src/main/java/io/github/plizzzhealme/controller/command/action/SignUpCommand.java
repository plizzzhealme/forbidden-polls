package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.EmailIsBusyException;
import io.github.plizzzhealme.service.exception.ServiceException;
import io.github.plizzzhealme.service.exception.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class SignUpCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException, ServletException {

        String email = request.getParameter(Util.USER_EMAIL);
        String password = request.getParameter(Util.USER_PASSWORD);
        String name = request.getParameter(Util.USER_NAME);
        String birthday = request.getParameter(Util.USER_BIRTHDAY);
        String country = request.getParameter(Util.USER_COUNTRY);
        String gender = request.getParameter(Util.USER_GENDER);

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCountry(country);
        user.setGender(gender);
        user.setUserRole(Util.USER);
        user.setRegistrationDate(LocalDateTime.now());

        try {
            user.setBirthday(LocalDate.parse(birthday));
        } catch (DateTimeParseException e) {
            user.setBirthday(null);
        }

        try {
            ServiceFactory.INSTANCE.getUserService().signUp(user);
            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SIGN_IN_PAGE_COMMAND);
        } catch (ValidationException e) {
            request.setAttribute(Util.ERROR, e.getMessage());

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_UP_JSP);
            dispatcher.forward(request, response);
        } catch (EmailIsBusyException e) {
            request.setAttribute(Util.ERROR, Util.EMAIL_IS_BUSY_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_UP_JSP);
            dispatcher.forward(request, response);
        }
    }
}
