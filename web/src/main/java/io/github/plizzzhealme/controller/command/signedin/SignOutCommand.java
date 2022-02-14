package io.github.plizzzhealme.controller.command.signedin;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();

        response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SIGN_IN_PAGE_COMMAND);
    }
}
