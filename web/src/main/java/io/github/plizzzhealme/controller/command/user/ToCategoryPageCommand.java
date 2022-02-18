package io.github.plizzzhealme.controller.command.user;

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
        String category = request.getParameter(Util.CATEGORY);
        int categoryId = findCategoryId(category);
        int userId = readUserId(request);
        SearchCriteria criteria = buildSearchCriteria(categoryId);
        List<Survey> surveys = searchAvailableSurveys(userId, criteria);
        saveRequestData(request, category, surveys);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.CATEGORY_JSP);
        dispatcher.forward(request, response);
    }

    private int findCategoryId(String category) throws ServiceException {
        return ServiceFactory.INSTANCE.getCategoryService().findId(category);
    }

    private int readUserId(HttpServletRequest request) {
        Object id = request.getSession().getAttribute(Util.USER_ID);

        if (id != null) {
            return (int) id;
        }

        return Util.NON_EXISTENT_ID;
    }

    private SearchCriteria buildSearchCriteria(int categoryId) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.addParameter(Parameter.SURVEY_CATEGORY_ID, categoryId);
        return criteria;
    }

    private List<Survey> searchAvailableSurveys(int userId, SearchCriteria criteria) throws ServiceException {
        return ServiceFactory.INSTANCE.getSurveyService().searchAvailableSurveys(criteria, userId);
    }

    private void saveRequestData(HttpServletRequest request, String category, List<Survey> surveys) {
        request.setAttribute(Util.SURVEY_LIST, surveys);
        request.setAttribute(Util.CATEGORY_NAME, category);
    }
}
