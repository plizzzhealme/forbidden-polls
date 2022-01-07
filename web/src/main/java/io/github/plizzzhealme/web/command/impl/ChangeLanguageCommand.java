package io.github.plizzzhealme.web.command.impl;

import io.github.plizzzhealme.web.command.Command;
import io.github.plizzzhealme.web.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter(WebUtil.LOCALE);
        request.getSession().setAttribute(WebUtil.LOCALE, locale);

        String url = (String) request.getSession().getAttribute(WebUtil.URL);
        response.sendRedirect(url);
    }
}
