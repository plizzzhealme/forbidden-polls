package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateSurveyHeaderCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String surveyName = request.getParameter(Util.SURVEY_NAME);
        String surveyDescription = request.getParameter(Util.SURVEY_DESCRIPTION);
        String surveyInstructions = request.getParameter(Util.SURVEY_INSTRUCTIONS);
        String surveyImageUrl = request.getParameter(Util.SURVEY_IMAGE_URL);
        String surveyCategory = request.getParameter(Util.SURVEY_CATEGORY);

        Survey survey = new Survey();
        survey.setName(surveyName);
        survey.setDescription(surveyDescription);
        survey.setInstructions(surveyInstructions);
        survey.setImageUrl(surveyImageUrl);
        survey.setCategory(surveyCategory);

        HttpSession session = request.getSession();
        session.setAttribute(Util.NEW_SURVEY, survey);

        response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SURVEY_QUESTIONS_CREATION_PAGE_COMMAND);
    }
}
