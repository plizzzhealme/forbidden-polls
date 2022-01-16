package io.github.plizzzhealme.bean.criteria;

public enum Column {

    SURVEYS_NAME("surveys.name"),
    SURVEYS_ID("surveys.id"),
    SURVEYS_CREATION_DATE("surveys.creation_date"),
    SURVEYS_DESCRIPTION("surveys.description"),
    SURVEYS_INSTRUCTIONS("surveys.instruction"),
    SURVEYS_IMAGE_URL("surveys.image_url"),
    SURVEYS_CATEGORY_ID("surveys.category_id"),

    QUESTIONS_ID("questions.id"),
    QUESTIONS_BODY("questions.body"),
    QUESTIONS_INDEX_NUMBER("questions.index_number"),
    QUESTIONS_CATEGORY_ID("questions.category_id"),

    OPTIONS_ID("options.id"),
    OPTIONS_INDEX_NUMBER("options.index_number"),
    OPTIONS_BODY("options.body");

    private final String value;

    Column(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}