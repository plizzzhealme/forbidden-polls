package io.github.plizzzhealme.dao.criteria;

public enum Column {
    SURVEYS_NAME("surveys.name"),
    SURVEYS_ID("surveys.id"),
    SURVEYS_CREATION_DATE("columns.creation_date"),
    SURVEYS_DESCRIPTION("surveys.description"),
    SURVEYS_INSTRUCTIONS("surveys.instruction"),
    SURVEYS_IMAGE_URL("surveys.image_url"),
    SURVEYS_CATEGORY_ID("surveys.category_id");

    private final String value;

    Column(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}