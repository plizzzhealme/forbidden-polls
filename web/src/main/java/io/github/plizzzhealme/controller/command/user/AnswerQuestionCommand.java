package io.github.plizzzhealme.controller.command.user;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.exception.EmptyInputException;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AnswerQuestionCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException, EmptyInputException {

        Survey survey = readSurvey(request);

        if (survey == null) {
            throw new EmptyInputException("Survey must not be null");
        }

        int questionIndex = readQuestionIndex(request);
        int answerIndex = readAnswerIndex(request);

        setAnswer(survey, questionIndex, answerIndex);
        questionIndex++;

        if (questionIndex < survey.getQuestions().size()) {
            saveRequestData(request, questionIndex);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_QUESTION_PAGE_COMMAND);
        } else {
            saveSurveyResult(request);
            clearSession(request);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SURVEY_COMPLETED_PAGE_COMMAND);
        }
    }

    private void setAnswer(Survey survey, int questionIndex, int answerIndex) {
        survey.getQuestions().get(questionIndex).setAnswerIndex(answerIndex);
    }

    private Survey readSurvey(HttpServletRequest request) {
        return (Survey) request.getSession().getAttribute(Util.SURVEY);
    }

    private int readAnswerIndex(HttpServletRequest request) throws EmptyInputException {
        try {
            return Integer.parseInt(request.getParameter(Util.OPTION));
        } catch (NumberFormatException e) {
            throw new EmptyInputException("Invalid answer Index");
        }
    }

    private int readQuestionIndex(HttpServletRequest request) {
        Object questionIndex = request.getSession().getAttribute(Util.QUESTION_INDEX);

        if (questionIndex != null) {
            return (int) questionIndex;
        }

        return 0;
    }

    private void saveRequestData(HttpServletRequest request, int questionIndex) {
        request.getSession().setAttribute(Util.QUESTION_INDEX, questionIndex);
    }

    private void saveSurveyResult(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute(Util.SURVEY);
        int userId = (int) session.getAttribute(Util.USER_ID);
        ServiceFactory.INSTANCE.getSurveyService().completeSurvey(survey, userId);
    }

    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Util.QUESTION_INDEX);
        session.removeAttribute(Util.SURVEY);
    }
}
