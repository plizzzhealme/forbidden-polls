package io.github.plizzzhealme.bean.criteria;

import java.io.Serializable;

public enum Parameter implements Serializable {

    USER_ID,
    USER_NAME,
    USER_EMAIL,
    USER_REGISTRATION_DATE,
    USER_BIRTHDAY,

    SURVEY_NAME,
    SURVEY_ID,
    SURVEY_CREATION_DATE,
    SURVEY_DESCRIPTION,
    SURVEY_INSTRUCTIONS,
    SURVEY_IMAGE_URL,
    SURVEY_CATEGORY_ID,

    QUESTION_ID,
    QUESTION_BODY,
    QUESTION_INDEX_NUMBER,
    QUESTION_CATEGORY_ID,

    OPTION_ID,
    OPTION_INDEX_NUMBER,
    GENDER_NAME,
    OPTION_BODY
}
