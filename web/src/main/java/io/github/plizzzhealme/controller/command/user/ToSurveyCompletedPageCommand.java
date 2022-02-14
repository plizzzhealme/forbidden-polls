package io.github.plizzzhealme.controller.command.user;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToSurveyCompletedPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(Util.SURVEY_COMPLETED_JSP).forward(request, response);

    }
}
