package io.github.plizzzhealme.web.command;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = new HashMap<>();
        commands.put("authorization", new AuthorizationCommand());
        commands.put("to_registration_page", new ToAuthorizationPageCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
