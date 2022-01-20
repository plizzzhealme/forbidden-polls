package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AnswerCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

    }
}
