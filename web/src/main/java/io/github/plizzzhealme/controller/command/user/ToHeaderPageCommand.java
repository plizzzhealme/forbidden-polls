package io.github.plizzzhealme.controller.command.user;

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

public class ToHeaderPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        int surveyId = readSurveyId(request);
        Survey survey = findSurvey(surveyId);
        saveRequestData(request, survey);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.HEADER_JSP);
        dispatcher.forward(request, response);
    }

    private int readSurveyId(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter(Util.SURVEY_ID));
        } catch (NumberFormatException e) {
            return Util.NON_EXISTENT_ID;
        }
    }

    private Survey findSurvey(int surveyId) throws ServiceException {
        return ServiceFactory.INSTANCE.getSurveyService().read(surveyId);
    }

    private void saveRequestData(HttpServletRequest request, Survey survey) {
        request.setAttribute(Util.SURVEY, survey);
    }
}
