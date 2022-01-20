package io.github.plizzzhealme.controller.command.navigation;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToCategoryPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String id = request.getParameter(Util.CATEGORY_ID);
        String categoryName = request.getParameter(Util.CATEGORY_NAME);

        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter(Parameter.SURVEY_CATEGORY_ID, id);

        List<Survey> surveys = ServiceFactory.INSTANCE.getSurveyService().search(criteria);
        request.setAttribute(Util.SURVEY_LIST, surveys);
        request.setAttribute(Util.CATEGORY_NAME, categoryName);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.CATEGORY_JSP);
        dispatcher.forward(request, response);
    }
}
