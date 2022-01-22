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

public class ToSurveyBeginPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        int surveyId = Integer.parseInt(request.getParameter(Util.SURVEY_ID));
        Survey survey = ServiceFactory.INSTANCE.getSurveyService().takeSurvey(surveyId);
        request.setAttribute(Util.SURVEY, survey);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SURVEY_BEGIN_JSP);
        dispatcher.forward(request, response);
    }
}