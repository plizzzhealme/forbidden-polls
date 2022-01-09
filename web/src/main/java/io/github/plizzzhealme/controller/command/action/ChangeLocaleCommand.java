package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.ControllerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String locale = request.getParameter(ControllerUtil.LOCALE);
        session.setAttribute(ControllerUtil.LOCALE, locale);

        String referer = request.getHeader(ControllerUtil.REFERER);

        if (referer == null) {
            referer = (String) session.getAttribute(ControllerUtil.URL);
        }

        response.sendRedirect(referer);
    }
}
