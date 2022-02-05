package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.exception.EmptyInputException;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.controller.validator.EmptyInputValidator;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddSurveyQuestionCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        String body = request.getParameter(Util.QUESTION_BODY);
        String description = request.getParameter(Util.QUESTION_DESCRIPTION);
        String imageUrl = request.getParameter(Util.QUESTION_IMAGE_URL);
        String[] optionValues = ArrayUtils.nullToEmpty(request.getParameterValues(Util.OPTION));

        try {
            EmptyInputValidator validator = EmptyInputValidator.getInstance();
            validator.validateEmptyInput(body);
            validator.validateEmptyInput(optionValues);

            List<Option> options = new ArrayList<>();

            for (String optionValue : optionValues) {
                Option option = new Option();
                option.setIndex(options.size());
                option.setBody(optionValue);
                options.add(option);
            }

            HttpSession session = request.getSession();
            Survey survey = (Survey) session.getAttribute(Util.NEW_SURVEY);
            List<Question> questions = survey.getQuestions();
            String editIndex = (String) session.getAttribute(Util.EDIT_INDEX);

            if (editIndex != null) {
                editQuestion(editIndex, body, description, imageUrl, options, questions);
                session.removeAttribute(Util.EDIT_INDEX);
            } else {
                addQuestion(body, description, imageUrl, options, questions);
            }

            response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND);
        } catch (EmptyInputException e) {
            request.setAttribute(Util.ERROR, Util.EMPTY_FIELDS_ERROR);

            RequestDispatcher dispatcher = request.getRequestDispatcher(Util.ADD_SURVEY_QUESTION_JSP);
            dispatcher.forward(request, response);
        }
    }

    private void addQuestion(String body,
                             String description,
                             String imageUrl,
                             List<Option> options,
                             List<Question> questions) {
        Question question = new Question();

        question.setIndex(questions.size());
        question.setBody(body);
        question.setDescription(description);
        question.setImageUrl(imageUrl);
        question.setOptionType(Question.SELECT);
        question.setOptions(options);

        questions.add(question);
    }

    private void editQuestion(String editIndex,
                              String body,
                              String description,
                              String imageUrl,
                              List<Option> options,
                              List<Question> questions) {

        Question question = questions.get(Integer.parseInt(editIndex));

        question.setBody(body);
        question.setDescription(description);
        question.setImageUrl(imageUrl);
        question.setOptionType(Question.SELECT);
        question.setOptions(options);
    }
}
