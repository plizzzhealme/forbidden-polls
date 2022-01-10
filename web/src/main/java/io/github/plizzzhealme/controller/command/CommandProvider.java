package io.github.plizzzhealme.controller.command;

import io.github.plizzzhealme.controller.command.action.AuthorizationCommand;
import io.github.plizzzhealme.controller.command.action.ChangeLocaleCommand;
import io.github.plizzzhealme.controller.command.action.RegistrationCommand;
import io.github.plizzzhealme.controller.command.action.UnknownCommand;
import io.github.plizzzhealme.controller.command.navigation.ToAuthorizationPageCommand;
import io.github.plizzzhealme.controller.command.navigation.ToRegistrationPageCommand;
import io.github.plizzzhealme.controller.command.navigation.ToStartPageCommand;
import io.github.plizzzhealme.controller.command.navigation.ToUserPageCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    public static final String COMMAND = "command";

    // actions
    public static final String AUTHORIZATION_COMMAND = "authorization";
    public static final String REGISTRATION_COMMAND = "registration";
    public static final String CHANGE_LOCALE_COMMAND = "change_locale";
    public static final String UNKNOWN_COMMAND = "unknown";

    // navigation
    public static final String TO_AUTHORIZATION_PAGE_COMMAND = "to_authorization_page";
    public static final String TO_REGISTRATION_PAGE_COMMAND = "to_registration_page";
    public static final String TO_START_PAGE_COMMAND = "to_start_page";
    public static final String TO_USER_PAGE_COMMAND = "to_user_page";

    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = new HashMap<>();

        commands.put(AUTHORIZATION_COMMAND, new AuthorizationCommand());
        commands.put(TO_AUTHORIZATION_PAGE_COMMAND, new ToAuthorizationPageCommand());
        commands.put(TO_REGISTRATION_PAGE_COMMAND, new ToRegistrationPageCommand());
        commands.put(TO_START_PAGE_COMMAND, new ToStartPageCommand());
        commands.put(TO_USER_PAGE_COMMAND, new ToUserPageCommand());
        commands.put(UNKNOWN_COMMAND, new UnknownCommand());
        commands.put(REGISTRATION_COMMAND, new RegistrationCommand());
        commands.put(CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
    }

    public Command getCommand(String commandName) {
        System.out.println(commandName);
        Command command = commands.get(commandName);

        if (command == null) {
            command = commands.get(UNKNOWN_COMMAND);
        }
        return command;
    }
}
