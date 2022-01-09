package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnknownCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(WebUtil.TO_UNFOUNDED_PAGE_REDIRECT);
    }
}
