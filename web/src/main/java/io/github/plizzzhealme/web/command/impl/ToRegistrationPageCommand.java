package io.github.plizzzhealme.web.command.impl;

import io.github.plizzzhealme.web.command.Command;
import io.github.plizzzhealme.web.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(WebUtil.REGISTRATION_JSP);
        dispatcher.forward(request, response);
    }
}
