package io.github.plizzzhealme.controller.command.admin;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.Parameter;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchUserCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        clearPreviousSearchData(request);
        SearchCriteria criteria = buildSearchCriteria(request);
        List<User> users = search(criteria);
        saveSearchData(request, criteria, users);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SEARCH_USER_JSP);
        dispatcher.forward(request, response);
    }

    private void clearPreviousSearchData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Util.SEARCH_CRITERIA);
        session.removeAttribute(Util.SEARCH_OFFSET);
    }

    private SearchCriteria buildSearchCriteria(HttpServletRequest request) {
        String name = request.getParameter(Util.USER_NAME);
        String email = request.getParameter(Util.USER_EMAIL);
        String gender = request.getParameter(Util.USER_GENDER);

        SearchCriteria criteria = new SearchCriteria();

        if (StringUtils.isNotBlank(name)) {
            criteria.addParameter(Parameter.USER_NAME, name);
        }

        if (StringUtils.isNotBlank(email)) {
            criteria.addParameter(Parameter.USER_EMAIL, email);
        }

        if (StringUtils.isNotBlank(gender)) {
            criteria.addParameter(Parameter.GENDER_NAME, gender);
        }

        return criteria;
    }

    private List<User> search(SearchCriteria criteria) throws ServiceException {
        return ServiceFactory.INSTANCE.getUserService().search(criteria, Util.SEARCH_LIMIT, Util.OFFSET_INIT_VALUE);
    }

    private void saveSearchData(HttpServletRequest request, SearchCriteria criteria, List<User> users) {
        request.setAttribute(Util.USER_LIST, users);

        HttpSession session = request.getSession();
        session.setAttribute(Util.SEARCH_CRITERIA, criteria);
        session.setAttribute(Util.SEARCH_OFFSET, Util.OFFSET_INIT_VALUE);
    }
}
