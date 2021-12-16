package io.github.plizzzhealme.web.command;

import io.github.plizzzhealme.web.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    public static final String COMMAND = "command";

    public static final String UNKNOWN = "unknown";
    public static final String AUTHORIZATION = "authorization";
    public static final String REGISTRATION = "registration";

    public static final String TO_AUTHORIZATION_PAGE = "to_authorization_page";
    public static final String TO_REGISTRATION_PAGE = "to_registration_page";
    public static final String TO_START_PAGE = "to_start_page";
    public static final String TO_USER_PAGE = "to_user_page";


    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = new HashMap<>();

        commands.put(AUTHORIZATION, new AuthorizationCommand());
        commands.put(TO_AUTHORIZATION_PAGE, new ToAuthorizationPageCommand());
        commands.put(TO_REGISTRATION_PAGE, new ToRegistrationPageCommand());
        commands.put(TO_START_PAGE, new ToStartPageCommand());
        commands.put(TO_USER_PAGE, new ToUserPageCommand());
        commands.put(UNKNOWN, new UnknownCommand());
        commands.put(REGISTRATION, new RegistrationCommand());
    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);

        if (command == null) {
            command = commands.get(UNKNOWN);
        }
        return command;
    }
}
