package io.github.plizzzhealme.controller.command.navigation;

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

public class ToEditProfileInfoPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(Util.USER_ID);
        User user = ServiceFactory.INSTANCE.getUserService().readUserInfo(id);
        request.setAttribute(Util.USER, user);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.EDIT_PROFILE_INFO_JSP);
        dispatcher.forward(request, response);
    }
}
