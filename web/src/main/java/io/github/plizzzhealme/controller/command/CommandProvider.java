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

        commands.put(Util.ADD_SURVEY_COMMAND, new AddSurveyCommand());
        commands.put(Util.ADD_SURVEY_HEADER_COMMAND, new AddSurveyHeaderCommand());
        commands.put(Util.ADD_SURVEY_QUESTION_COMMAND, new AddSurveyQuestionCommand());
        commands.put(Util.ANSWER_SURVEY_QUESTION_COMMAND, new AnswerSurveyQuestionCommand());
        commands.put(Util.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(Util.EDIT_PROFILE_INFO_COMMAND, new EditProfileInfoCommand());
        commands.put(Util.EDIT_SURVEY_COMMAND, new EditSurveyCommand());
        commands.put(Util.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(Util.SIGN_OUT_COMMAND, new SignOutCommand());
        commands.put(Util.SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(Util.TAKE_SURVEY_COMMAND, new TakeSurveyCommand());
        commands.put(Util.UNKNOWN_COMMAND, new UnknownCommand());

        commands.put(Util.TO_ADD_SURVEY_HEADER_PAGE_COMMAND, new ToAddSurveyHeaderPageCommand());
        commands.put(Util.TO_ADD_SURVEY_PAGE_COMMAND, new ToAddSurveyPageCommand());
        commands.put(Util.TO_ADD_SURVEY_QUESTION_PAGE_COMMAND, new ToAddSurveyQuestionPageCommand());
        commands.put(Util.TO_CATEGORIES_PAGE_COMMAND, new ToCategoriesPageCommand());
        commands.put(Util.TO_CATEGORY_PAGE_COMMAND, new ToCategoryPageCommand());
        commands.put(Util.TO_COMPLETED_SURVEYS_PAGE_COMMAND, new ToCompletedSurveysPageCommand());
        commands.put(Util.TO_EDIT_PROFILE_INFO_PAGE_COMMAND, new ToEditProfileInfoPageCommand());
        commands.put(Util.TO_HOME_PAGE_COMMAND, new ToHomePageCommand());
        commands.put(Util.TO_PROFILE_INFO_PAGE_COMMAND, new ToProfileInfoPageCommand());
        commands.put(Util.TO_PROFILE_PAGE_COMMAND, new ToProfilePageCommand());
        commands.put(Util.TO_SIGN_IN_PAGE_COMMAND, new ToSignInPageCommand());
        commands.put(Util.TO_SIGN_UP_PAGE_COMMAND, new ToSignUpPageCommand());
        commands.put(Util.TO_SURVEY_ADDED_PAGE_COMMAND, new ToSurveyAddedPageCommand());
        commands.put(Util.TO_SURVEY_COMPLETED_PAGE_COMMAND, new ToSurveyCompletedPageCommand());
        commands.put(Util.TO_SURVEY_HEADER_PAGE_COMMAND, new ToSurveyHeaderPageCommand());
        commands.put(Util.TO_SURVEY_QUESTION_PAGE_COMMAND, new ToSurveyQuestionPageCommand());


    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);

        if (command == null) {
            command = commands.get(Util.UNKNOWN_COMMAND);
        }
        return command;
    }
}
