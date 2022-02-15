package io.github.plizzzhealme.controller.command.admin;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.bean.criteria.SearchCriteria;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchNextUserCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        HttpSession session = request.getSession();
        SearchCriteria criteria = (SearchCriteria) session.getAttribute(Util.SEARCH_CRITERIA);
        int offset = (int) session.getAttribute(Util.SEARCH_OFFSET);
        int nextOffset = offset + Util.SEARCH_LIMIT;

        UserService userService = ServiceFactory.INSTANCE.getUserService();
        List<User> users = userService.search(criteria, Util.SEARCH_LIMIT, nextOffset);

        if (users.isEmpty()) {
            users = userService.search(criteria, Util.SEARCH_LIMIT, offset); // repeat previous search
        } else {
            session.setAttribute(Util.SEARCH_OFFSET, nextOffset);
        }

        request.setAttribute(Util.USER_LIST, users);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.SEARCH_USER_JSP);
        dispatcher.forward(request, response);
    }
}
