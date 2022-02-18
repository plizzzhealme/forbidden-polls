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
import java.util.List;

public class ToCompletedSurveysPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int userId = readUserId(request);
        List<Survey> surveys = searchCompletedSurveys(userId);
        saveRequestData(request, surveys);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.COMPLETED_SURVEYS_JSP);
        dispatcher.forward(request, response);
    }

    private int readUserId(HttpServletRequest request) {
        Object id = request.getSession().getAttribute(Util.USER_ID);

        if (id != null) {
            return (int) id;
        }

        return Util.NON_EXISTENT_ID;
    }

    private List<Survey> searchCompletedSurveys(int userId) throws ServiceException {
        return ServiceFactory.INSTANCE.getSurveyService().searchCompletedSurveys(userId);
    }

    private void saveRequestData(HttpServletRequest request, List<Survey> surveys) {
        request.setAttribute(Util.SURVEY_LIST, surveys);
    }
}
