package io.github.plizzzhealme.web.command.impl;

import io.github.plizzzhealme.web.command.Command;
import io.github.plizzzhealme.web.command.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter(WebUtil.EMAIL));
        System.out.println(request.getParameter(WebUtil.NAME));
        System.out.println(request.getParameter(WebUtil.PASSWORD));
        System.out.println(request.getParameter(WebUtil.CONFIRM_PASSWORD));
        System.out.println(request.getParameter(WebUtil.GENDER));
        System.out.println(request.getParameter(WebUtil.COUNTRY));
    }
}
