package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditSurveyCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String edit = request.getParameter("edit");

        if (edit.equals("header")) {
            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_ADD_SURVEY_HEADER_PAGE_COMMAND);
        } else {
            request.getSession().setAttribute("edit_index", edit);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND);
        }
    }
}
