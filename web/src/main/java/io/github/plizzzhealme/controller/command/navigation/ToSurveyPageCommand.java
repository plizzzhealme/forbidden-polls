package io.github.plizzzhealme.controller.command.navigation;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.ControllerUtil;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToSurveyPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int id = 1; //stub for int id = Integer.parseInt(request.getParameter("survey_id"));

        Survey survey = ServiceFactory.INSTANCE.getSurveyService().takeSurvey(id);
        System.out.println(survey);
        request.setAttribute("survey_name", survey.getName());

        RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerUtil.SURVEY_JSP);
        dispatcher.forward(request, response);
    }
}