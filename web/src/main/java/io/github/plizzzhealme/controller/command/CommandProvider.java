package io.github.plizzzhealme.controller.command;

import io.github.plizzzhealme.controller.command.admin.*;
import io.github.plizzzhealme.controller.command.guest.*;
import io.github.plizzzhealme.controller.command.signedin.*;
import io.github.plizzzhealme.controller.command.user.*;
import io.github.plizzzhealme.controller.util.Util;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = new HashMap<>();

        // admin
        commands.put(Util.ADD_HEADER_COMMAND, new AddHeaderCommand());
        commands.put(Util.ADD_QUESTION_COMMAND, new AddQuestionCommand());
        commands.put(Util.ADD_SURVEY_COMMAND, new AddSurveyCommand());
        commands.put(Util.BLOCK_USER_COMMAND, new BlockUserCommand());
        commands.put(Util.EDIT_SURVEY_COMMAND, new EditSurveyCommand());
        commands.put(Util.SEARCH_NEXT_USER_COMMAND, new SearchNextUserCommand());
        commands.put(Util.SEARCH_PREVIOUS_USER_COMMAND, new SearchPreviousUserCommand());
        commands.put(Util.SEARCH_SURVEY_COMMAND, new SearchSurveyCommand());
        commands.put(Util.SEARCH_USER_COMMAND, new SearchUserCommand());
        commands.put(Util.TO_ADD_HEADER_PAGE_COMMAND, new ToAddHeaderPageCommand());
        commands.put(Util.TO_ADD_QUESTION_PAGE_COMMAND, new ToAddQuestionPageCommand());
        commands.put(Util.TO_ADD_SURVEY_PAGE_COMMAND, new ToAddSurveyPageCommand());
        commands.put(Util.TO_SEARCH_SURVEY_PAGE_COMMAND, new ToSearchSurveyPageCommand());
        commands.put(Util.TO_SEARCH_USER_PAGE_COMMAND, new ToSearchUserPageCommand());
        commands.put(Util.TO_SURVEY_ADDED_PAGE_COMMAND, new ToSurveyAddedPageCommand());
        commands.put(Util.TO_SURVEY_PAGE_COMMAND, new ToSurveyPageCommand());
        commands.put(Util.TO_USER_PAGE_COMMAND, new ToUserPageCommand());

        // guest
        commands.put(Util.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(Util.SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(Util.TO_HOME_PAGE_COMMAND, new ToHomePageCommand());
        commands.put(Util.TO_SIGN_IN_PAGE_COMMAND, new ToSignInPageCommand());
        commands.put(Util.TO_SIGN_UP_PAGE_COMMAND, new ToSignUpPageCommand());

        // everyone who signed in
        commands.put(Util.EDIT_PROFILE_INFO_COMMAND, new EditProfileInfoCommand());
        commands.put(Util.SIGN_OUT_COMMAND, new SignOutCommand());
        commands.put(Util.TO_EDIT_PROFILE_INFO_PAGE_COMMAND, new ToEditProfileInfoPageCommand());
        commands.put(Util.TO_PROFILE_INFO_PAGE_COMMAND, new ToProfileInfoPageCommand());
        commands.put(Util.TO_PROFILE_PAGE_COMMAND, new ToProfilePageCommand());

        // user
        commands.put(Util.ANSWER_QUESTION_COMMAND, new AnswerQuestionCommand());
        commands.put(Util.TAKE_SURVEY_COMMAND, new TakeSurveyCommand());
        commands.put(Util.TO_CATEGORIES_PAGE_COMMAND, new ToCategoriesPageCommand());
        commands.put(Util.TO_CATEGORY_PAGE_COMMAND, new ToCategoryPageCommand());
        commands.put(Util.TO_COMPLETED_SURVEYS_PAGE_COMMAND, new ToCompletedSurveysPageCommand());
        commands.put(Util.TO_HEADER_PAGE_COMMAND, new ToHeaderPageCommand());
        commands.put(Util.TO_QUESTION_PAGE_COMMAND, new ToQuestionPageCommand());
        commands.put(Util.TO_STATISTICS_PAGE_COMMAND, new ToStatisticsPageCommand());
        commands.put(Util.TO_SURVEY_COMPLETED_PAGE_COMMAND, new ToSurveyCompletedPageCommand());

        // everyone
        commands.put(Util.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(Util.UNKNOWN_COMMAND, new UnknownCommand());
    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);

        if (command == null) {
            command = commands.get(Util.UNKNOWN_COMMAND);
        }
        return command;
    }
}
