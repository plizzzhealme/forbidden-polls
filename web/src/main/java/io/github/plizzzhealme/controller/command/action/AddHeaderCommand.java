package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AddHeaderCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();

        // required
        String surveyName = request.getParameter(Util.SURVEY_NAME);
        String surveyCategory = request.getParameter(Util.SURVEY_CATEGORY);

        // optional
        String surveyDescription = request.getParameter(Util.SURVEY_DESCRIPTION);
        String surveyInstructions = request.getParameter(Util.SURVEY_INSTRUCTIONS);
        String surveyImageUrl = request.getParameter(Util.SURVEY_IMAGE_URL);


        if (Util.isAnyBlank(surveyName, surveyCategory)) {
            request.setAttribute(Util.ERROR, Util.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.ADD_SURVEY_HEADER_JSP);
            dispatcher.forward(request, response);
        } else {
            Survey survey = new Survey();
            survey.setName(surveyName);
            survey.setDescription(surveyDescription);
            survey.setInstructions(surveyInstructions);
            survey.setImageUrl(surveyImageUrl);
            survey.setCategory(surveyCategory);
            survey.setQuestions(new ArrayList<>());

            session.setAttribute(Util.NEW_SURVEY, survey);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND);
        }
    }
}
