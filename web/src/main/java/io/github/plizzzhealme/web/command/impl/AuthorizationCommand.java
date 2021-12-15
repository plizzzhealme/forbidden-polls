package io.github.plizzzhealme.web.command.impl;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.service.Service;
import io.github.plizzzhealme.web.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    // @SuppressWarnings("all")
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("AuthorizationCommand 0");
        User user = new Service().authorize(email, password);
        System.out.println("AuthorizationCommand 1");

        password = null;

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect("controller?command=to_user_page");
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Authorization.jsp");
            dispatcher.forward(request, response);
        }
    }
}
