package io.github.plizzzhealme.bean;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Survey implements Serializable {

    @Serial
    private static final long serialVersionUID = 6485520742536097834L;

    private int id;
    private String name;
    private LocalDateTime creationDate;
    private String description;
    private String instructions;
    private String imageUrl;
    private String category;
    private List<Question> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Survey survey = (Survey) o;

        return getId() == survey.getId()
                && Objects.equals(getName(), survey.getName())
                && Objects.equals(getCreationDate(), survey.getCreationDate())
                && Objects.equals(getDescription(), survey.getDescription())
                && Objects.equals(getInstructions(), survey.getInstructions())
                && Objects.equals(getImageUrl(), survey.getImageUrl())
                && Objects.equals(getCategory(), survey.getCategory())
                && Objects.equals(getQuestions(), survey.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getCreationDate(),
                getDescription(),
                getInstructions(),
                getImageUrl(),
                getCategory(),
                getQuestions());
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", instructions='" + instructions + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", questions=" + questions +
                ", creationDate=" + creationDate +
                '}';
    }
}
