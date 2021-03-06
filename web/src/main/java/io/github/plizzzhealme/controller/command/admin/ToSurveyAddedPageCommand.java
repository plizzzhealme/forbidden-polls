package io.github.plizzzhealme.controller.command.admin;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToSurveyAddedPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SURVEY_ADDED_JSP);
        dispatcher.forward(request, response);
    }
}
