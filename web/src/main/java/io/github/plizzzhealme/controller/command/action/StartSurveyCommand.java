package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.SurveyService;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartSurveyCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        SurveyService surveyService = ServiceFactory.INSTANCE.getSurveyService();
        Survey survey = surveyService.takeSurvey(Integer.parseInt(request.getParameter("survey_id")));
        request.setAttribute("status", "1");

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SURVEY_JSP);
        dispatcher.forward(request, response);
    }
}
