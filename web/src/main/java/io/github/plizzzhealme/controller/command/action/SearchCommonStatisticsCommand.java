package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.CategoryService;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchCommonStatisticsCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String title = request.getParameter(Util.SURVEY_TITLE);
        String category = request.getParameter(Util.CATEGORY);

        SearchCriteria criteria = new SearchCriteria();

        if (StringUtils.isNotBlank(title)) {
            criteria.addParameter(Parameter.SURVEY_NAME, title);
        }

        if (StringUtils.isNotBlank(category)) {
            CategoryService categoryService = ServiceFactory.INSTANCE.getCategoryService();
            int categoryId = categoryService.findId(category);
            criteria.addParameter(Parameter.SURVEY_CATEGORY_ID, categoryId);
        }

        List<Survey> surveys = ServiceFactory.INSTANCE.getSurveyService().search(criteria);
        request.setAttribute(Util.SURVEY_LIST, surveys);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SEARCH_COMMON_STATISTICS_JSP);
        dispatcher.forward(request, response);
    }
}
