package io.github.plizzzhealme.controller.command;

import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String locale = request.getParameter(Util.LOCALE);
        session.setAttribute(Util.LOCALE, locale);

        response.sendRedirect((String) session.getAttribute(Util.URL));
    }
}
