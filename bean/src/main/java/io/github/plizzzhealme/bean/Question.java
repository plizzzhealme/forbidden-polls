package io.github.plizzzhealme.bean;

import java.util.List;

public class Question {

    public static final String SELECT = "select";
    public static final String MULTI_SELECT = "multi-select";
    public static final String CUSTOM = "custom";

    private int id;
    private int index;
    private String body;
    private List<String> options;
    private String optionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }
}
