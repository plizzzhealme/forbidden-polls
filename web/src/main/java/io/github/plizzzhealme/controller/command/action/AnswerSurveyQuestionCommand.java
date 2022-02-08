package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AnswerSurveyQuestionCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException {

        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute(Util.SURVEY);
        int questionIndex = (int) session.getAttribute(Util.QUESTION_INDEX);
        int answerIndex = Integer.parseInt(request.getParameter(Util.OPTION));
        survey.getQuestions().get(questionIndex).setAnswerIndex(answerIndex);

        questionIndex++;

        if (questionIndex < survey.getQuestions().size()) {
            session.setAttribute(Util.QUESTION_INDEX, questionIndex);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SURVEY_QUESTION_PAGE_COMMAND);
        } else {
            finishSurvey(request);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SURVEY_COMPLETED_PAGE_COMMAND);
        }
    }

    private void finishSurvey(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute(Util.SURVEY);
        int userId = (int) session.getAttribute(Util.USER_ID);
        session.removeAttribute(Util.QUESTION_INDEX);
        session.removeAttribute(Util.SURVEY);
        ServiceFactory.INSTANCE.getSurveyService().completeSurvey(survey, userId);
    }
}
