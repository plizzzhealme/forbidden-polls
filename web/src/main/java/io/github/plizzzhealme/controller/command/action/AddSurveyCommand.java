package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class AddSurveyCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();

        Survey survey = (Survey) session.getAttribute(Util.NEW_SURVEY);
        survey.setCreationDate(LocalDateTime.now());
        ServiceFactory.INSTANCE.getSurveyService().addNewSurvey(survey);

        session.removeAttribute(Util.NEW_SURVEY);

        response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SURVEY_ADDED_PAGE_COMMAND);
    }
}
