package io.github.plizzzhealme.web;

import io.github.plizzzhealme.service.DatabaseConnectionService;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.web.command.Command;
import io.github.plizzzhealme.web.command.CommandProvider;

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
        DatabaseConnectionService databaseConnectionService = ServiceFactory.getDatabaseConnectionService();
        databaseConnectionService.connect();
    }

    @Override
    public void destroy() {
        DatabaseConnectionService databaseConnectionService = ServiceFactory.getDatabaseConnectionService();
        databaseConnectionService.disconnect();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(CommandProvider.COMMAND);
        Command command = commandProvider.getCommand(commandName);
        command.execute(request, response);
    }
}
