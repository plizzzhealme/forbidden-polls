package io.github.plizzzhealme.controller.command.action;

import io.github.plizzzhealme.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // coming soon
    }
}
