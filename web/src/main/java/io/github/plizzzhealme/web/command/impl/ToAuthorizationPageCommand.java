package io.github.plizzzhealme.web.command.impl;

import io.github.plizzzhealme.web.command.Command;
import io.github.plizzzhealme.web.util.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ToAuthorizationPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = request.getRequestURL().append("?").append(request.getQueryString()).toString();
        session.setAttribute(WebUtil.URL, url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(WebUtil.AUTHORIZATION_JSP);
        dispatcher.forward(request, response);
    }
}
