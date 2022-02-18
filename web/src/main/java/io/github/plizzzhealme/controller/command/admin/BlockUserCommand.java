package io.github.plizzzhealme.controller.command.admin;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.UserService;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, ServletException, IOException {

        int id = readUserId(request);
        blockUser(id);
        User user = readUserInfo(id);
        saveRequestData(request, user);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.USER_JSP);
        dispatcher.forward(request, response);
    }

    private void saveRequestData(HttpServletRequest request, User user) {
        request.setAttribute(Util.USER, user);
    }

    private User readUserInfo(int id) throws ServiceException {
        return ServiceFactory.INSTANCE.getUserService().readUserInfo(id);
    }

    private int readUserId(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter(Util.USER_ID));
        } catch (NumberFormatException e) {
            return Util.NON_EXISTENT_ID;
        }
    }

    void blockUser(int id) throws ServiceException {
        UserService userService = ServiceFactory.INSTANCE.getUserService();
        userService.blockUser(id);
    }
}
