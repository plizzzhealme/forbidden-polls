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

public class AnswerCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute(Util.SURVEY);
        int questionIndex = (int) session.getAttribute(Util.QUESTION_INDEX);

        questionIndex++;

        if (questionIndex < survey.getQuestions().size()) {
            session.setAttribute(Util.QUESTION_INDEX, questionIndex);
            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SURVEY_PAGE_COMMAND);
        } else {

            finishSurvey(request, response);
            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_SURVEY_END_PAGE_COMMAND);
        }
    }

    private void finishSurvey(HttpServletRequest request, HttpServletResponse response) {
        //todo save result
        HttpSession session = request.getSession();
        session.removeAttribute(Util.QUESTION_INDEX);
        session.removeAttribute(Util.SURVEY);
    }
}
