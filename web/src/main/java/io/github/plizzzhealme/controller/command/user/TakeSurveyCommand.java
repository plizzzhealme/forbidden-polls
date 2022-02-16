package io.github.plizzzhealme.controller.command.user;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TakeSurveyCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        int surveyId = readSurveyId(request);
        Survey survey = findSurvey(surveyId);
        saveRequestData(request, survey);

        response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_QUESTION_PAGE_COMMAND);
    }

    private int readSurveyId(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter(Util.SURVEY_ID));
        } catch (NumberFormatException e) {
            return Util.NON_EXISTENT_ID;
        }
    }

    private Survey findSurvey(int surveyId) throws ServiceException {
        return ServiceFactory.INSTANCE.getSurveyService().takeSurvey(surveyId);
    }

    private void saveRequestData(HttpServletRequest request, Survey survey) {
        HttpSession session = request.getSession();
        session.setAttribute(Util.SURVEY, survey);
        session.setAttribute(Util.QUESTION_INDEX, 0);
    }
}
