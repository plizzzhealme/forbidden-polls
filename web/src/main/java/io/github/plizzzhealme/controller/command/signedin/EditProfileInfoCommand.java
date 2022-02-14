package io.github.plizzzhealme.controller.command.signedin;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.ServiceException;
import io.github.plizzzhealme.service.exception.ValidatorException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EditProfileInfoCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        UserService userService = ServiceFactory.INSTANCE.getUserService();

        HttpSession session = request.getSession();

        int id = (int) session.getAttribute(Util.USER_ID);

        User user = userService.readUserInfo(id);

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
            LocalDate date;

            try {
                date = LocalDate.parse(birthday);
            } catch (DateTimeParseException e) {
                date = null;
            }

            if (date != null) {
                user.setBirthday(date);
            }
        }

        if (StringUtils.isNotBlank(country)) {
            user.setCountry(country);
        }

        if (StringUtils.isNotBlank(gender)) {
            user.setGender(gender);
        }

        try {
            userService.updateUserInfo(user);
            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_PROFILE_INFO_PAGE_COMMAND);
        } catch (ValidatorException e) {
            request.setAttribute(Util.ERROR, e.getMessage());

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.EDIT_PROFILE_INFO_JSP);
            dispatcher.forward(request, response);
        }
    }
}
