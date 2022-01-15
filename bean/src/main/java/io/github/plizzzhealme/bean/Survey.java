package io.github.plizzzhealme.bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Survey {

    private int id;
    private String category;
    private String name;
    private String description;
    private String instructions;
    private String imageUrl;
    private List<Question> questions;
    private LocalDateTime creationDate;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return id == survey.id && Objects.equals(category, survey.category) && Objects.equals(name, survey.name) && Objects.equals(description, survey.description) && Objects.equals(instructions, survey.instructions) && Objects.equals(imageUrl, survey.imageUrl) && Objects.equals(questions, survey.questions) && Objects.equals(creationDate, survey.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, description, instructions, imageUrl, questions, creationDate);
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
