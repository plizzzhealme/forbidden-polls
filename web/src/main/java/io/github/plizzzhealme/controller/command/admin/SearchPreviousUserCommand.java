package io.github.plizzzhealme.controller.command.admin;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchPreviousUserCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        SearchCriteria criteria = readSearchCriteria(request);
        int offset = readSearchOffset(request);

        if (criteria != null) {
            offset = Math.max(0, offset - Util.SEARCH_LIMIT);
            List<User> users = search(criteria, offset);
            saveSearchData(request, offset, users);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SEARCH_USER_JSP);
        dispatcher.forward(request, response);
    }

    private void saveSearchData(HttpServletRequest request, int offset, List<User> users) {
        HttpSession session = request.getSession();
        request.setAttribute(Util.USER_LIST, users);
        session.setAttribute(Util.SEARCH_OFFSET, offset);
    }

    private List<User> search(SearchCriteria criteria, int offset) throws ServiceException {
        return ServiceFactory.INSTANCE.getUserService().search(criteria, Util.SEARCH_LIMIT, offset);
    }

    private int readSearchOffset(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object offsetObject = session.getAttribute(Util.SEARCH_OFFSET);

        if (offsetObject != null) {
            return (int) offsetObject;
        }

        return Util.OFFSET_INIT_VALUE;
    }

    private SearchCriteria readSearchCriteria(HttpServletRequest request) {
        return (SearchCriteria) request.getSession().getAttribute(Util.SEARCH_CRITERIA);
    }
}
