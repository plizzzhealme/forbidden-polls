package io.github.plizzzhealme.controller;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.command.CommandProvider;
import io.github.plizzzhealme.controller.exception.ControllerException;
import io.github.plizzzhealme.controller.util.WebUtil;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void init(ServletConfig config) {
        try {
            ServiceFactory.getDatabaseConnectionService().connect();
        } catch (ServiceException e) {
            e.printStackTrace();
            // todo add logger
            throw new ControllerException();
        }
    }

    @Override
    public void destroy() {
        ServiceFactory.getDatabaseConnectionService().disconnect();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) {
        String commandName = request.getParameter(CommandProvider.COMMAND);
        Command command = commandProvider.getCommand(commandName);

        try {
            command.execute(request, response);
        } catch (ServletException | IOException | ServiceException e) {
            e.printStackTrace();
            // todo add logger

            try {
                request.getSession(true).setAttribute(WebUtil.ERROR, "redirected");
                response.sendRedirect(WebUtil.TO_SERVER_ERROR_PAGE_REDIRECT);
            } catch (IOException ex) {
                request.getSession(true).setAttribute(WebUtil.ERROR, "web.xml");
                throw new ControllerException();
            }
        }
    }
}
