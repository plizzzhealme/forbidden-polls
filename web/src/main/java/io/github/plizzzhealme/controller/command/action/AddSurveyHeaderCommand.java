package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.exception.EmptyInputException;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.controller.validator.EmptyInputValidator;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AddSurveyHeaderCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        String surveyName = request.getParameter(Util.SURVEY_NAME);
        String surveyCategory = request.getParameter(Util.SURVEY_CATEGORY);
        String surveyDescription = request.getParameter(Util.SURVEY_DESCRIPTION);
        String surveyInstructions = request.getParameter(Util.SURVEY_INSTRUCTIONS);
        String surveyImageUrl = request.getParameter(Util.SURVEY_IMAGE_URL);

        try {
            EmptyInputValidator.getInstance().validateEmptyInput(surveyName, surveyCategory);

            HttpSession session = request.getSession();
            Survey survey = (Survey) session.getAttribute(Util.NEW_SURVEY);

            if (survey == null) {
                survey = new Survey();
                session.setAttribute(Util.NEW_SURVEY, survey);
                survey.setQuestions(new ArrayList<>());
            }

            survey.setName(surveyName);
            survey.setDescription(surveyDescription);
            survey.setInstructions(surveyInstructions);
            survey.setImageUrl(surveyImageUrl);
            survey.setCategory(surveyCategory);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND);
        } catch (EmptyInputException e) {
            request.setAttribute(Util.ERROR, Util.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.ADD_SURVEY_HEADER_JSP);
            dispatcher.forward(request, response);
        }
    }
}
