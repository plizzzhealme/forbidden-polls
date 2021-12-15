package io.github.plizzzhealme.web.command;

import io.github.plizzzhealme.web.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = new HashMap<>();

        commands.put("authorization", new AuthorizationCommand());
        commands.put("to_authorization_page", new ToAuthorizationPageCommand());
        commands.put("to_registration_page", new ToRegistrationPageCommand());
        commands.put("to_start_page", new ToStartPageCommand());
        commands.put("to_user_page", new ToUserPageCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
