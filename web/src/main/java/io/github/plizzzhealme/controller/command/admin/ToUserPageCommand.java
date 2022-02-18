package io.github.plizzzhealme.controller.command.admin;

import io.github.plizzzhealme.bean.User;
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

public class ToUserPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        clearSearchData(request);

        int id = readId(request);
        User user = ServiceFactory.INSTANCE.getUserService().readUserInfo(id);
        request.setAttribute(Util.USER, user);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.USER_JSP);
        dispatcher.forward(request, response);
    }

    private void clearSearchData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Util.SEARCH_OFFSET);
        session.removeAttribute(Util.SEARCH_CRITERIA);
    }

    private int readId(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter(Util.USER_ID));
        } catch (NumberFormatException e) {
            return Util.NON_EXISTENT_ID;
        }
    }
}
