package io.github.plizzzhealme.bean;

import java.util.List;
import java.util.Objects;

public class Question {

    private int id;
    private int index;
    private String body;
    private String imageUrl;
    private String description;
    private List<Option> options;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && index == question.index && Objects.equals(body, question.body) && Objects.equals(imageUrl, question.imageUrl) && Objects.equals(description, question.description) && Objects.equals(options, question.options) && Objects.equals(optionType, question.optionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, index, body, imageUrl, description, options, optionType);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", index=" + index +
                ", body='" + body + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                ", optionType='" + optionType + '\'' +
                '}';
    }
}
