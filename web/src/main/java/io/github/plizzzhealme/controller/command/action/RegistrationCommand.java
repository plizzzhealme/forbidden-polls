package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.ControllerUtil;
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
        String email = request.getParameter(ControllerUtil.EMAIL);
        String name = request.getParameter(ControllerUtil.NAME);
        String password = request.getParameter(ControllerUtil.PASSWORD);
        String confirmPassword = request.getParameter(ControllerUtil.CONFIRM_PASSWORD);
        LocalDateTime registrationDate = LocalDateTime.now();
        String birthday = request.getParameter(ControllerUtil.BIRTHDAY);
        String userRole = ControllerUtil.USER;
        String country = request.getParameter(ControllerUtil.COUNTRY);
        String gender = request.getParameter(ControllerUtil.GENDER);

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
                response.sendRedirect(ControllerUtil.TO_AUTHORIZATION_PAGE_REDIRECT);
            } else {
                // todo add message with a reason of creation error
            }
        }
    }
}
