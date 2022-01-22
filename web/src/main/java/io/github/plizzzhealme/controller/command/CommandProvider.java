package io.github.plizzzhealme.controller.command;

import io.github.plizzzhealme.controller.command.action.*;
import io.github.plizzzhealme.controller.command.navigation.*;
import io.github.plizzzhealme.controller.util.Util;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = new HashMap<>();

        commands.put(Util.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(Util.TO_SIGN_IN_PAGE_COMMAND, new ToSignInPageCommand());
        commands.put(Util.TO_SIGN_UP_PAGE_COMMAND, new ToSignUpPageCommand());
        commands.put(Util.TO_START_PAGE_COMMAND, new ToStartPageCommand());
        commands.put(Util.TO_PROFILE_PAGE_COMMAND, new ToProfilePageCommand());
        commands.put(Util.UNKNOWN_COMMAND, new UnknownCommand());
        commands.put(Util.SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(Util.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(Util.SIGN_OUT_COMMAND, new SignOutCommand());
        commands.put(Util.TO_SURVEY_PAGE_COMMAND, new ToSurveyPageCommand());
        commands.put(Util.TO_PROFILE_INFO_PAGE_COMMAND, new ToProfileInfoPageCommand());
        commands.put(Util.TO_CATEGORIES_PAGE_COMMAND, new ToCategoriesPageCommand());
        commands.put(Util.TO_CATEGORY_PAGE_COMMAND, new ToCategoryPageCommand());
        commands.put(Util.START_SURVEY_COMMAND, new StartSurveyCommand());
        commands.put(Util.ANSWER_COMMAND, new AnswerCommand());
        commands.put(Util.TO_SURVEY_END_PAGE_COMMAND, new ToSurveyEndPageCommand());
        commands.put(Util.TO_SURVEY_BEGIN_PAGE_COMMAND, new ToSurveyBeginPageCommand());
    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);

        if (command == null) {
            command = commands.get(Util.UNKNOWN_COMMAND);
        }
        return command;
    }
}
