package io.github.plizzzhealme.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Option implements Serializable {

    @Serial
    private static final long serialVersionUID = -4859356782695712359L;

    private int id;
    private String body;
    private int index;

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
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Option option = (Option) o;

        return getId() == option.getId()
                && getIndex() == option.getIndex()
                && Objects.equals(getBody(), option.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBody(), getIndex());
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
