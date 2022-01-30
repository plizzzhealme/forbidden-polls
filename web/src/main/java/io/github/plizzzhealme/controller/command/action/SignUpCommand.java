package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SignUpCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        String email = request.getParameter(Util.USER_EMAIL);
        String name = request.getParameter(Util.USER_NAME);
        String password = request.getParameter(Util.USER_PASSWORD);
        String confirmPassword = request.getParameter(Util.USER_CONFIRM_PASSWORD);
        LocalDateTime registrationDate = LocalDateTime.now();
        String birthday = request.getParameter(Util.USER_BIRTHDAY);
        String userRole = Util.USER;
        String country = request.getParameter(Util.USER_COUNTRY);
        String gender = request.getParameter(Util.USER_GENDER);

        if (StringUtils.isAnyBlank(email, name, password, confirmPassword, birthday, country, gender)) {
            request.setAttribute(Util.ERROR, Util.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_UP_JSP);
            dispatcher.forward(request, response);
        } else { // if entered
            if (!StringUtils.equals(password, confirmPassword)) {
                request.setAttribute(Util.ERROR, Util.PASSWORD_MISMATCH_ERROR);

                RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_UP_JSP);
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
                    user = ServiceFactory.INSTANCE.getUserService().authorize(email, password);
                    HttpSession session = request.getSession();
                    session.setAttribute(Util.USER_ID, user.getId());
                    session.setAttribute(Util.USER_ROLE, user.getUserRole());

                    response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_PROFILE_PAGE_COMMAND);
                } else {
                    request.setAttribute(Util.ERROR, Util.EMAIL_IS_BUSY_ERROR);

                    RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SIGN_UP_JSP);
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}
