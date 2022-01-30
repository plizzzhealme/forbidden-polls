package io.github.plizzzhealme.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Question implements Serializable {

    public static final String SELECT = "select";

    @Serial
    private static final long serialVersionUID = 7809120840553064347L;

    private int id;
    private int index;
    private String body;
    private String imageUrl;
    private String description;
    private List<Option> options;
    private String optionType;
    private int answerIndex;

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

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Question question = (Question) o;

        return getId() == question.getId()
                && getIndex() == question.getIndex()
                && getAnswerIndex() == question.getAnswerIndex()
                && Objects.equals(getBody(), question.getBody())
                && Objects.equals(getImageUrl(), question.getImageUrl())
                && Objects.equals(getDescription(), question.getDescription())
                && Objects.equals(getOptions(), question.getOptions())
                && Objects.equals(getOptionType(), question.getOptionType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getIndex(),
                getBody(),
                getImageUrl(),
                getDescription(),
                getOptions(),
                getOptionType(),
                getAnswerIndex());
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
                ", answerIndex=" + answerIndex +
                '}';
    }
}
