package io.github.plizzzhealme.controller.command.signedin;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;
import io.github.plizzzhealme.service.exception.ValidatorException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EditProfileInfoCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        int id = readId(request);
        User user = findUser(id);
        updateUser(request, user);

        try {
            ServiceFactory.INSTANCE.getUserService().updateUserInfo(user);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_PROFILE_INFO_PAGE_COMMAND);
        } catch (ValidatorException e) {
            request.setAttribute(Util.ERROR, e.getMessage());

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.EDIT_PROFILE_INFO_JSP);
            dispatcher.forward(request, response);
        }
    }

    private int readId(HttpServletRequest request) {
        Object id = request.getSession().getAttribute(Util.USER_ID);

        if (id != null) {
            return (int) id;
        }

        return Util.NON_EXISTENT_ID;
    }

    private User findUser(int id) throws ServiceException {
        return ServiceFactory.INSTANCE.getUserService().readUserInfo(id);
    }

    private void updateUser(HttpServletRequest request, User user) {
        String email = request.getParameter(Util.USER_EMAIL);
        String name = request.getParameter(Util.USER_NAME);
        String birthday = request.getParameter(Util.USER_BIRTHDAY);
        String country = request.getParameter(Util.USER_COUNTRY);
        String gender = request.getParameter(Util.USER_GENDER);

        if (StringUtils.isNotBlank(email)) {
            user.setEmail(email);
        }

        if (StringUtils.isNotBlank(name)) {
            user.setName(name);
        }

        if (StringUtils.isNotBlank(birthday)) {
            try {
                LocalDate date = LocalDate.parse(birthday);
                user.setBirthday(date);
            } catch (DateTimeParseException ignored) {
                // act like we didn't change birthday
            }
        }

        if (StringUtils.isNotBlank(country)) {
            user.setCountry(country);
        }

        if (StringUtils.isNotBlank(gender)) {
            user.setGender(gender);
        }
    }
}
