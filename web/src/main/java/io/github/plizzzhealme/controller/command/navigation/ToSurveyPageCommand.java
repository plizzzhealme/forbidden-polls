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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ToSurveyPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute(Util.SURVEY);

        if (survey == null) {
            int surveyId = Integer.parseInt(request.getParameter(Util.SURVEY_ID));
            survey = ServiceFactory.INSTANCE.getSurveyService().takeSurvey(surveyId);
            request.setAttribute(Util.SURVEY, survey);
        } else {
            request.setAttribute(Util.SURVEY, survey);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SURVEY_JSP);
        dispatcher.forward(request, response);
    }
}
