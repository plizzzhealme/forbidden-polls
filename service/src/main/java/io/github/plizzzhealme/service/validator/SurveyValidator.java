package io.github.plizzzhealme.service.validator;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SurveyValidator {

    public static final int MIN_OPTIONS_NUMBER = 2;
    private static final SurveyValidator INSTANCE = new SurveyValidator();

    private SurveyValidator() {
    }

    public static SurveyValidator getInstance() {
        return INSTANCE;
    }

    public boolean isValidSurvey(Survey survey) {
        if (StringUtils.isAnyBlank(survey.getName(), survey.getCategory())) {
            return false;
        }

        List<Question> questions = survey.getQuestions();

        if (questions == null || questions.isEmpty()) {
            return false;
        }

        return questions.stream().allMatch(this::isValidQuestion);
    }


    public boolean isValidQuestion(Question question) {
        if (question == null) {
            return false;
        }

        if (StringUtils.isAnyBlank(question.getBody(), question.getOptionType())) {
            return false;
        }

        String optionType = question.getOptionType();

        if (!StringUtils.equalsAnyIgnoreCase(optionType, Question.SELECT, Question.MULTI_SELECT, Question.CUSTOM)) {
            return false;
        }

        List<Option> options = question.getOptions();

        if (options == null) {
            return false;
        }

        if (StringUtils.equalsAny(optionType, Question.SELECT, Question.MULTI_SELECT)
                && options.size() < MIN_OPTIONS_NUMBER) {
            return false;
        }

        return options.stream().allMatch(this::isValidOption);
    }

    public boolean isValidOption(Option option) {
        if (option == null) {
            return false;
        }

        return StringUtils.isNotBlank(option.getBody());
    }
}
