package io.github.plizzzhealme.bean;

import java.util.Objects;

public class Option {

    private int id;
    private int index;
    private String body;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id == option.id && index == option.index && Objects.equals(body, option.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, index, body);
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", index=" + index +
                ", body='" + body + '\'' +
                '}';
    }
}
