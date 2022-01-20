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
        // int optionId = Integer.parseInt(request.getParameter(Util.OPTION));
        int questionIndex = (int) session.getAttribute(Util.QUESTION_INDEX);

        questionIndex++;

        if (questionIndex < survey.getQuestions().size()) {
            session.setAttribute(Util.QUESTION_INDEX, questionIndex);
            response.sendRedirect("controller?command=to_survey_page");
        } else {
            response.sendRedirect("controller?command=to_survey_passed_page");
        }
    }
}
