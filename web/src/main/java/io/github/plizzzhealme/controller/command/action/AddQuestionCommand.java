package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.exception.ServiceException;

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
        // todo add question
        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute(Util.NEW_SURVEY);

        String questionBody = request.getParameter(Util.QUESTION);

        int optionsNumber = Integer.parseInt(request.getParameter(Util.OPTIONS_NUMBER));
        Question question = new Question();
        question.setBody(questionBody);
        List<Option> optionList = new ArrayList<>();

        for (int i = 0; i < optionsNumber; i++) {
            Option option = new Option();
            option.setIndex(i);
            option.setBody(request.getParameter("option" + i));
            optionList.add(option);
        }
        question.setOptionType("select");
        question.setOptions(optionList);


        survey.getQuestions().add(question);
        System.out.println(survey);


        response.sendRedirect(Util.REDIRECT_URL_PATTERN + Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND);
    }
}
