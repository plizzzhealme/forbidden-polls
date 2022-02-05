package io.github.plizzzhealme.service.validator;

import io.github.plizzzhealme.bean.Option;
import io.github.plizzzhealme.bean.Question;
import io.github.plizzzhealme.bean.Survey;
import io.github.plizzzhealme.service.exception.ValidatorException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SurveyValidator {

    private static final SurveyValidator INSTANCE = new SurveyValidator();

    private static final int MIN_OPTIONS_NUMBER = 2;

    private SurveyValidator() {
    }

    public static SurveyValidator getInstance() {
        return INSTANCE;
    }

    public void validateSurvey(Survey survey) throws ValidatorException {
        validateHeader(survey);

        for (Question question : survey.getQuestions()) {
            validateQuestion(question);

            for (Option option : question.getOptions()) {
                validateOption(option);
            }
        }
    }

    public void validateHeader(Survey survey) throws ValidatorException {
        if (survey == null) {
            throw new ValidatorException("Empty survey");
        }

        if (StringUtils.isBlank(survey.getName())) {
            throw new ValidatorException("Empty survey name");
        }

        if (StringUtils.isBlank(survey.getCategory())) {
            throw new ValidatorException("Empty survey category");
        }

        List<Question> questions = survey.getQuestions();

        if (questions == null || questions.isEmpty()) {
            throw new ValidatorException("Empty question list");
        }
    }

    public void validateQuestion(Question question) throws ValidatorException {
        if (question == null || StringUtils.isBlank(question.getBody())) {
            throw new ValidatorException("Empty question");
        }

        String optionType = question.getOptionType();

        if (!StringUtils.equalsAnyIgnoreCase(optionType,
                Question.SELECT,
                Question.MULTI_SELECT,
                Question.CUSTOM)) {

            throw new ValidatorException("Invalid option type in question: " + question.getBody());
        }

        List<Option> options = question.getOptions();

        if (options == null) {
            throw new ValidatorException("Empty options in question " + question.getBody());
        }

        int optionsNumber = options.size();

        if (StringUtils.equalsAnyIgnoreCase(optionType, Question.SELECT, Question.MULTI_SELECT)
                && optionsNumber < MIN_OPTIONS_NUMBER) {

            throw new ValidatorException("Invalid number of options in question " + question.getBody());
        }

    }

    public void validateOption(Option option) throws ValidatorException {
        if (option == null || StringUtils.isBlank(option.getBody())) {
            throw new ValidatorException("Empty option");
        }
    }
}
