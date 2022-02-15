package io.github.plizzzhealme.controller.command.user;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ToCompletedSurveysPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(Util.USER_ID);
        SurveyService surveyService = ServiceFactory.INSTANCE.getSurveyService();
        List<Survey> surveys = surveyService.searchCompletedSurveys(userId);

        request.setAttribute(Util.SURVEY_LIST, surveys);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.COMPLETED_SURVEYS_JSP);
        dispatcher.forward(request, response);
    }
}
