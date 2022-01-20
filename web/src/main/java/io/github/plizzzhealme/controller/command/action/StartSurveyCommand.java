package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.SurveyService;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartSurveyCommand implements Command {

    private static final Logger logger = LogManager.getLogger(StartSurveyCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        int surveyId = 0;
        boolean isCorrectId = false;

        try {
            surveyId = Integer.parseInt(request.getParameter(Util.SURVEY_ID));
            isCorrectId = true;
        } catch (NumberFormatException e) {
            logger.error("Invalid survey id", e);
            response.sendRedirect(Util.PAGE_NOT_FOUND_JSP);
        }

        if (isCorrectId) {
            SurveyService surveyService = ServiceFactory.INSTANCE.getSurveyService();
            Survey survey = surveyService.takeSurvey(surveyId);
            HttpSession session = request.getSession();
            System.out.println(survey);
            session.setAttribute(Util.SURVEY, survey);
            session.setAttribute(Util.QUESTION_INDEX, 0);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SURVEY_JSP);
            dispatcher.forward(request, response);
        }
    }
}
