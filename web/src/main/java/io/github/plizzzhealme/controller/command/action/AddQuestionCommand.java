package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
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
import java.util.List;

public class AddQuestionCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        // required
        String body = request.getParameter(Util.QUESTION);

        // optional
        String description = request.getParameter(Util.QUESTION_OPTION);
        String imageUrl = request.getParameter(Util.QUESTION_IMAGE_URL);

        List<Option> options = new ArrayList<>();
        String[] optionValues = request.getParameterValues(Util.OPTION);

        if (optionValues != null) {
            for (int i = 0; i < optionValues.length; i++) {
                Option option = new Option();
                option.setIndex(i);
                option.setBody(optionValues[i]);
                options.add(option);
            }
        }

        if (Util.isAnyBlank(body) || Util.isAnyBlank(optionValues)) {
            request.setAttribute(Util.ERROR, Util.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.ADD_SURVEY_QUESTION_JSP);
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            Survey survey = (Survey) session.getAttribute(Util.NEW_SURVEY);

            Question question = new Question();

            question.setBody(body);
            question.setDescription(description);
            question.setImageUrl(imageUrl);
            question.setOptionType(Question.SELECT);
            question.setOptions(options);

            survey.getQuestions().add(question);

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND);
        }
    }
}
