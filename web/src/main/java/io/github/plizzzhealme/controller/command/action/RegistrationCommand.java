package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.ControllerUtil;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        String email = request.getParameter(ControllerUtil.EMAIL);
        String name = request.getParameter(ControllerUtil.NAME);
        String password = request.getParameter(ControllerUtil.PASSWORD);
        String confirmPassword = request.getParameter(ControllerUtil.CONFIRM_PASSWORD);
        LocalDateTime registrationDate = LocalDateTime.now();
        String birthday = request.getParameter(ControllerUtil.BIRTHDAY);
        String userRole = ControllerUtil.USER;
        String country = request.getParameter(ControllerUtil.COUNTRY);
        String gender = request.getParameter(ControllerUtil.GENDER);

        if (StringUtils.isAnyBlank(email, name, password, confirmPassword, birthday, country, gender)) {
            request.setAttribute(ControllerUtil.ERROR_MESSAGE, ControllerUtil.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.REGISTRATION_JSP);
            dispatcher.forward(request, response);
        } else { // if entered
            if (!StringUtils.equals(password, confirmPassword)) {
                request.setAttribute(ControllerUtil.ERROR_MESSAGE, ControllerUtil.PASSWORD_MISMATCH_ERROR);

                RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.REGISTRATION_JSP);
                dispatcher.forward(request, response);
            } else {
                User user = new User();

                user.setEmail(email);
                user.setCountry(country);
                user.setUserRole(userRole);
                user.setGender(gender);
                user.setName(name);
                user.setRegistrationDate(registrationDate);
                user.setBirthday(LocalDate.parse(birthday));

                boolean isRegistered = ServiceFactory.INSTANCE.getUserService().register(user, password);

                if (isRegistered) {
                    int userID = ServiceFactory.INSTANCE.getUserService().authorize(email, password);
                    request.getSession().setAttribute(ControllerUtil.USER_ID, userID);

                    response.sendRedirect(ControllerUtil.TO_USER_PAGE_REDIRECT);
                } else {
                    request.setAttribute(ControllerUtil.ERROR_MESSAGE, ControllerUtil.EMAIL_IS_BUSY_ERROR);

                    RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.REGISTRATION_JSP);
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}
