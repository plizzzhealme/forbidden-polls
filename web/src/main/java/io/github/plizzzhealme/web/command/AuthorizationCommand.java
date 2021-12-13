package io.github.plizzzhealme.web.command;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = Service.authorize(email, password);


    }
}
