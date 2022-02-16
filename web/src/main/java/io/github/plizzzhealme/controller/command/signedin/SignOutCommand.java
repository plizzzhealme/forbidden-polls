package io.github.plizzzhealme.controller.command.signedin;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        invalidateSession(request);

        response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SIGN_IN_PAGE_COMMAND);
    }

    private void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session != null) {
            session.invalidate();
        }
    }
}
