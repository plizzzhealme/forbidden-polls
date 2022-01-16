package io.github.plizzzhealme.controller.command;

import io.github.plizzzhealme.controller.command.action.*;
import io.github.plizzzhealme.controller.command.navigation.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    public static final String COMMAND = "command";

    // actions
    public static final String SIGN_IN_COMMAND = "sign_in";
    public static final String SIGN_UP_COMMAND = "sign_up";
    public static final String CHANGE_LOCALE_COMMAND = "change_locale";
    public static final String UNKNOWN_COMMAND = "unknown";
    public static final String SIGN_OUT_COMMAND = "sign_out";

    // navigation
    public static final String TO_SIGN_IN_PAGE_COMMAND = "to_sign_in_page";
    public static final String TO_SIGN_UP_PAGE_COMMAND = "to_sign_up_page";
    public static final String TO_START_PAGE_COMMAND = "to_start_page";
    public static final String TO_USER_PAGE_COMMAND = "to_user_page";
    public static final String TO_SURVEY_PAGE_COMMAND = "to_survey_page";
    public static final String TO_PROFILE_PAGE_COMMAND = "to_profile_page";
    public static final String TO_CATEGORIES_PAGE_COMMAND = "to_categories_page";
    public static final String TO_CATEGORY_PAGE_COMMAND = "to_category_page";

    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = new HashMap<>();

        commands.put(SIGN_IN_COMMAND, new AuthorizationCommand());
        commands.put(TO_SIGN_IN_PAGE_COMMAND, new ToSignInPageCommand());
        commands.put(TO_SIGN_UP_PAGE_COMMAND, new ToSignUpPageCommand());
        commands.put(TO_START_PAGE_COMMAND, new ToStartPageCommand());
        commands.put(TO_USER_PAGE_COMMAND, new ToUserPageCommand());
        commands.put(UNKNOWN_COMMAND, new UnknownCommand());
        commands.put(SIGN_UP_COMMAND, new RegistrationCommand());
        commands.put(CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(SIGN_OUT_COMMAND, new SignOutCommand());
        commands.put(TO_SURVEY_PAGE_COMMAND, new ToSurveyPageCommand());
        commands.put(TO_PROFILE_PAGE_COMMAND, new ToProfilePageCommand());
        commands.put(TO_CATEGORIES_PAGE_COMMAND, new ToCategoriesPageCommand());
        commands.put(TO_CATEGORY_PAGE_COMMAND, new ToCategoryPageCommand());
    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);

        if (command == null) {
            command = commands.get(UNKNOWN_COMMAND);
        }
        return command;
    }
}
