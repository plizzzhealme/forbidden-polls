package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.WebUtil;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        String email = request.getParameter(WebUtil.EMAIL);
        String name = request.getParameter(WebUtil.NAME);
        String password = request.getParameter(WebUtil.PASSWORD);
        String confirmPassword = request.getParameter(WebUtil.CONFIRM_PASSWORD);
        LocalDateTime registrationDate = LocalDateTime.now();
        String birthday = request.getParameter(WebUtil.BIRTHDAY);
        String userRole = WebUtil.USER;
        String country = request.getParameter(WebUtil.COUNTRY);
        String gender = request.getParameter(WebUtil.GENDER);

        User user = new User();

        if (birthday != null && !birthday.isEmpty()) {
            user.setBirthday(LocalDate.parse(birthday));
        }
        user.setEmail(email);
        user.setCountry(country);
        user.setUserRole(userRole);
        user.setGender(gender);
        user.setName(name);
        user.setRegistrationDate(registrationDate);

        if (password != null && !password.isEmpty() && password.equals(confirmPassword)) {
            UserService userService = ServiceFactory.getUserService();
            boolean isCreated = userService.register(user, password);

            if (isCreated) {
                response.sendRedirect(WebUtil.TO_AUTHORIZATION_PAGE_REDIRECT);
            } else {
                // todo add message with a reason of creation error
            }
        }
    }
}
