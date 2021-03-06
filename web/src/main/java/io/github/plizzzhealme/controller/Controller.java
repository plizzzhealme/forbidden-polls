package io.github.plizzzhealme.controller;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.command.CommandProvider;
import io.github.plizzzhealme.controller.exception.ControllerException;
import io.github.plizzzhealme.controller.exception.EmptyInputException;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.text.MessageFormat;

public class Controller extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 819081927994556627L;

    private static final Logger logger = LogManager.getLogger(Controller.class);

    private final transient CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String commandName = request.getParameter(Util.COMMAND);
        Command command = commandProvider.getCommand(commandName);

        try {
            command.execute(request, response);
        } catch (ServletException | IOException | ServiceException | EmptyInputException e) {
            logger.error(MessageFormat.format("Error processing a request for a command {0}.", commandName), e);

            try {
                response.sendRedirect(Util.PAGE_NOT_FOUND_JSP);
            } catch (IOException ex) {
                logger.error("Error while redirecting to the error page.", ex);

                throw new ControllerException();
            }
        }
    }
}
