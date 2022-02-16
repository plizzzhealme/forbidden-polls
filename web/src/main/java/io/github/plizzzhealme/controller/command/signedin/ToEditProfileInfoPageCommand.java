package io.github.plizzzhealme.controller.command.signedin;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToEditProfileInfoPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int id = readId(request);
        User user = findUser(id);
        saveProfileInfo(request, user);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.EDIT_PROFILE_INFO_JSP);
        dispatcher.forward(request, response);
    }

    private int readId(HttpServletRequest request) {
        Object id = request.getSession().getAttribute(Util.USER_ID);

        if (id != null) {
            return (int) id;
        }

        return Util.NON_EXISTENT_ID;
    }

    private User findUser(int id) throws ServiceException {
        return ServiceFactory.INSTANCE.getUserService().readUserInfo(id);
    }

    private void saveProfileInfo(HttpServletRequest request, User user) {
        request.setAttribute(Util.USER, user);
    }
}
