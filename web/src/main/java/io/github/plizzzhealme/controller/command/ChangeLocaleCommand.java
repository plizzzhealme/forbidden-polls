package io.github.plizzzhealme.controller.command;

import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        saveLocaleParameter(request);
        String redirectUrl = readRedirectUrl(request);

        response.sendRedirect(redirectUrl);
    }

    private String readRedirectUrl(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(Util.URL);
    }

    private void saveLocaleParameter(HttpServletRequest request) {
        String locale = request.getParameter(Util.LOCALE);
        request.getSession().setAttribute(Util.LOCALE, locale);
    }
}
