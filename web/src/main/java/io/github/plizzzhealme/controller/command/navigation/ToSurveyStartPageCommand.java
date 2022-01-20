package io.github.plizzzhealme.controller.command.navigation;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToSurveyStartPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int id = Integer.parseInt(request.getParameter("survey_id"));

        Survey survey = ServiceFactory.INSTANCE.getSurveyService().takeSurvey(id);
        request.setAttribute("survey", survey);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SURVEY_JSP);
        dispatcher.forward(request, response);
    }
}
